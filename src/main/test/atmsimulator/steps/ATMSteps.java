package atmsimulator.steps;

import atmsimulator.config.MockSecurityContext;
import atmsimulator.model.Account;
import atmsimulator.model.dto.WithdrawDTO;
import atmsimulator.repository.AccountRepository;
import atmsimulator.services.UserServices;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;


public class ATMSteps implements En {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserServices userServices;

    @Autowired
    @Mock
    private AccountRepository accountRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        final MockHttpServletRequestBuilder defaultRequestBuilder = get("/");

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .defaultRequest(defaultRequestBuilder)
                .alwaysDo(result -> setSessionBackOnRequestBuilder(defaultRequestBuilder, result.getRequest()))
                .build();
    }

    private void setSessionBackOnRequestBuilder(MockHttpServletRequestBuilder defaultRequestBuilder, MockHttpServletRequest request) {
        defaultRequestBuilder.session((MockHttpSession) request.getSession());
    }

    public ATMSteps() {
        Given("^authenticated user has a balance of (\\d+)$", (Integer balance) -> {
            mockMvc.perform(get("/signin"))
                    .andExpect(status().isOk());
            mockMvc.perform(post("/signin")
                    .param("username", "112233")
                    .param("password", "012108"))
                    .andExpect(status().is2xxSuccessful());
        });
        And("^user performs a withdrawal of (\\d+)$", (Integer amount) -> {
            mockMvc.perform(post("/submitWithdraw").with(new RequestPostProcessor() {
                public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                    request.setUserPrincipal(new Principal() {
                        @Override
                        public String getName() {
                            return "112233";
                        }
                    });
                    return request;
                }
            })
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .content(buildUrlEncodedFormEntity(
                            "amount", String.valueOf(amount))))
                    .andExpect(status().is3xxRedirection());
        });
        Then("^the balance is (\\d+)$", (Integer currentBalance) -> {
            MvcResult result = mockMvc.perform(get("/user").with(new RequestPostProcessor() {
                public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                    request.setUserPrincipal(new Principal() {
                        @Override
                        public String getName() {
                            return "112233";
                        }
                    });
                    return request;
                }
            })).andExpect(status().isOk()).andReturn();
            if (result.getModelAndView() != null) {
                Map<String, Object> model = result.getModelAndView().getModel();
                assertEquals(currentBalance, model.get("balance"));
            }
        });
        And("^user gets the list of transactions from newest to oldest$", () -> {
            mockMvc.perform(get("/transaction-logs").with(new RequestPostProcessor() {
                public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                    request.setUserPrincipal(new Principal() {
                        @Override
                        public String getName() {
                            return "112233";
                        }
                    });
                    return request;
                }
            })).andExpect(status().is2xxSuccessful());
        });
    }


    private String buildUrlEncodedFormEntity(String... params) {
        if ((params.length % 2) > 0) {
            throw new IllegalArgumentException("Need to give an even number of parameters");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < params.length; i += 2) {
            if (i > 0) {
                result.append('&');
            }
            try {
                result.
                        append(URLEncoder.encode(params[i], StandardCharsets.UTF_8.name())).
                        append('=').
                        append(URLEncoder.encode(params[i + 1], StandardCharsets.UTF_8.name()));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return result.toString();
    }
}
