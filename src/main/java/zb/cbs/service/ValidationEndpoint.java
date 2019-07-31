package zb.cbs.service;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.bpc.webgate.ws.cbs.zb.Account;
import ru.bpc.webgate.ws.cbs.zb.GetAccountBalance;
import ru.bpc.webgate.ws.cbs.zb.GetAccountBalanceResponse;
import ru.bpc.webgate.ws.cbs.zb.GetAccountDetails;
import ru.bpc.webgate.ws.cbs.zb.GetAccountDetailsResponse;
import ru.bpc.webgate.ws.cbs.zb.GetAccounts;
import ru.bpc.webgate.ws.cbs.zb.GetAccountsResponse;
import ru.bpc.webgate.ws.cbs.zb.ObjectFactory;
import ru.bpc.webgate.ws.cbs.zb.ProcessMiniStatement;
import ru.bpc.webgate.ws.cbs.zb.ProcessMiniStatementResponse;
import ru.bpc.webgate.ws.cbs.zb.Response;
import ru.bpc.webgate.ws.cbs.zb.ValidateAccount;
import ru.bpc.webgate.ws.cbs.zb.ValidateAccountResponse;
import ru.bpc.webgate.ws.cbs.zb.ValidateAgentAccount;
import ru.bpc.webgate.ws.cbs.zb.ValidateAgentAccountResponse;
import ru.bpc.webgate.ws.cbs.zb.ValidateCustomer;
import ru.bpc.webgate.ws.cbs.zb.ValidateCustomerResponse;
import ru.bpc.webgate.ws.cbs.zb.ValidateMobileNumber;
import ru.bpc.webgate.ws.cbs.zb.ValidateMobileNumberResponse;

import javax.xml.bind.JAXBElement;
import java.math.BigDecimal;


@Endpoint
public class ValidationEndpoint {

	private static final String NAMESPACE_URI = "http://ws.validations.bet.datacentre.co.zw/";
	private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "validateCustomer")
	@ResponsePayload
	public JAXBElement<ValidateCustomerResponse> validateCustomer(@RequestPayload JAXBElement<ValidateCustomer> req) {
		Response resp = new Response();
		resp.setNarrative("Successful");
		resp.setStatus("Successful");
		ValidateCustomerResponse response = new ValidateCustomerResponse();
		response.setReturn(resp);
		return OBJECT_FACTORY.createValidateCustomerResponse(response);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "validateAccount")
	@ResponsePayload
	public JAXBElement<ValidateAccountResponse> validateAccount(@RequestPayload JAXBElement<ValidateAccount> req) {
		Response resp = new Response();
		resp.setNarrative("Successful");
		resp.setStatus("Successful");
		ValidateAccountResponse response = new ValidateAccountResponse();
		response.setReturn(resp);
		return OBJECT_FACTORY.createValidateAccountResponse(response);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "processMiniStatement")
	@ResponsePayload
	public JAXBElement<ProcessMiniStatementResponse> processMiniStatement(@RequestPayload JAXBElement<ProcessMiniStatement> req) {
		ProcessMiniStatementResponse response = new ProcessMiniStatementResponse();
		response.setReturn("Successful");
		return OBJECT_FACTORY.createProcessMiniStatementResponse(response);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAccounts")
	@ResponsePayload
	public JAXBElement<GetAccountsResponse> getAccounts(@RequestPayload JAXBElement<GetAccounts> req) {
		GetAccountsResponse response = new GetAccountsResponse();
		GetAccountDetails details = new GetAccountDetails();
		details.setAccountNumber("1234567890");
		response.getReturn().add(getAccountDetails(OBJECT_FACTORY.createGetAccountDetails(details)).getValue().getReturn());
		return OBJECT_FACTORY.createGetAccountsResponse(response);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "validateAgentAccount")
	@ResponsePayload
	public JAXBElement<ValidateAgentAccountResponse> validateAgentAccount(@RequestPayload JAXBElement<ValidateAgentAccount> req) {
		ValidateAgentAccountResponse response = new ValidateAgentAccountResponse();
		response.setReturn("Successful");
		return OBJECT_FACTORY.createValidateAgentAccountResponse(response);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAccountBalance")
	@ResponsePayload
	public JAXBElement<GetAccountBalanceResponse> getAccountBalance(@RequestPayload JAXBElement<GetAccountBalance> req) {
		GetAccountBalanceResponse response = new GetAccountBalanceResponse();
		GetAccountDetails details = new GetAccountDetails();
		details.setAccountNumber(req.getValue().getAccountNumber());
		response.setReturn(
				getAccountDetails(OBJECT_FACTORY.createGetAccountDetails(details)).getValue().getReturn()
		);
		return OBJECT_FACTORY.createGetAccountBalanceResponse(response);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAccountDetails")
	@ResponsePayload
	public JAXBElement<GetAccountDetailsResponse> getAccountDetails(@RequestPayload JAXBElement<GetAccountDetails> req) {
		Account account = new Account();
		account.setAccountType("CHECKING");
		account.setAccountNumber(req.getValue().getAccountNumber());
		account.setAvailableBalance(new BigDecimal(1000000));
		account.setLedgerBalance(new BigDecimal(1000000));
		account.setCurrency("ZWL");
		GetAccountDetailsResponse response = new GetAccountDetailsResponse();
		response.setReturn(account);
		return OBJECT_FACTORY.createGetAccountDetailsResponse(response);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "validateMobileNumber")
	@ResponsePayload
	public JAXBElement<ValidateMobileNumberResponse> validateMobileNumber(@RequestPayload JAXBElement<ValidateMobileNumber> req) {
		Response resp = new Response();
		resp.setNarrative("Successful");
		resp.setStatus("Successful");
		ValidateMobileNumberResponse response = new ValidateMobileNumberResponse();
		response.setReturn(resp);
		return OBJECT_FACTORY.createValidateMobileNumberResponse(response);
	}
}
