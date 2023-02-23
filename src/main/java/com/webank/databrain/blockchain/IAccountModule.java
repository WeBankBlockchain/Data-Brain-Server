package com.webank.databrain.blockchain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.codec.datatypes.Address;
import org.fisco.bcos.sdk.v3.codec.datatypes.Event;
import org.fisco.bcos.sdk.v3.codec.datatypes.Function;
import org.fisco.bcos.sdk.v3.codec.datatypes.StaticStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Type;
import org.fisco.bcos.sdk.v3.codec.datatypes.TypeReference;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint8;
import org.fisco.bcos.sdk.v3.contract.Contract;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.CryptoType;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class IAccountModule extends Contract {
    public static final String[] BINARY_ARRAY = {};

    public static final String BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"did\",\"type\":\"bytes32\"}],\"name\":\"AccountAlreadyAudited\",\"type\":\"error\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"did\",\"type\":\"bytes32\"}],\"name\":\"AccountAlreadyExists\",\"type\":\"error\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"did\",\"type\":\"bytes32\"}],\"name\":\"AccountNotExist\",\"type\":\"error\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"AddressAlreadyExists\",\"type\":\"error\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"AddressNotRegistered\",\"type\":\"error\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"did\",\"type\":\"bytes32\"}],\"name\":\"InvalidAccountId\",\"type\":\"error\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"did\",\"type\":\"bytes32\"}],\"name\":\"InvalidAccountStatus\",\"type\":\"error\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"bytes32\",\"name\":\"did\",\"type\":\"bytes32\"}],\"name\":\"AccountApproved\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"bytes32\",\"name\":\"did\",\"type\":\"bytes32\"}],\"name\":\"AccountDenied\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"bytes32\",\"name\":\"did\",\"type\":\"bytes32\"},{\"indexed\":false,\"internalType\":\"address\",\"name\":\"addr\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"enum IAccountModule.AccountType\",\"name\":\"accountType\",\"type\":\"uint8\"},{\"indexed\":false,\"internalType\":\"bytes32\",\"name\":\"hash\",\"type\":\"bytes32\"}],\"name\":\"AccountRegistered\",\"type\":\"event\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"getAccountByAddress\",\"outputs\":[{\"components\":[{\"internalType\":\"bytes32\",\"name\":\"did\",\"type\":\"bytes32\"},{\"internalType\":\"address\",\"name\":\"addr\",\"type\":\"address\"},{\"internalType\":\"enum IAccountModule.AccountStatus\",\"name\":\"status\",\"type\":\"uint8\"},{\"internalType\":\"enum IAccountModule.AccountType\",\"name\":\"accountType\",\"type\":\"uint8\"},{\"internalType\":\"bytes32\",\"name\":\"hash\",\"type\":\"bytes32\"}],\"internalType\":\"struct IAccountModule.AccountData\",\"name\":\"\",\"type\":\"tuple\"}],\"selector\":[3102236652,745516914],\"stateMutability\":\"view\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_GETACCOUNTBYADDRESS = "getAccountByAddress";

    public static final Event ACCOUNTAPPROVED_EVENT = new Event("AccountApproved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
    ;

    public static final Event ACCOUNTDENIED_EVENT = new Event("AccountDenied", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
    ;

    public static final Event ACCOUNTREGISTERED_EVENT = new Event("AccountRegistered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}));
    ;

    protected IAccountModule(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static String getABI() {
        return ABI;
    }

    public List<AccountApprovedEventResponse> getAccountApprovedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ACCOUNTAPPROVED_EVENT, transactionReceipt);
        ArrayList<AccountApprovedEventResponse> responses = new ArrayList<AccountApprovedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AccountApprovedEventResponse typedResponse = new AccountApprovedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.did = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<AccountDeniedEventResponse> getAccountDeniedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ACCOUNTDENIED_EVENT, transactionReceipt);
        ArrayList<AccountDeniedEventResponse> responses = new ArrayList<AccountDeniedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AccountDeniedEventResponse typedResponse = new AccountDeniedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.did = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<AccountRegisteredEventResponse> getAccountRegisteredEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ACCOUNTREGISTERED_EVENT, transactionReceipt);
        ArrayList<AccountRegisteredEventResponse> responses = new ArrayList<AccountRegisteredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AccountRegisteredEventResponse typedResponse = new AccountRegisteredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.did = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.accountType = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.hash = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public IAccountModule.AccountData getAccountByAddress(String addr) throws ContractException {
        final Function function = new Function(FUNC_GETACCOUNTBYADDRESS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<IAccountModule.AccountData>() {}));
        return executeCallWithSingleValueReturn(function, IAccountModule.AccountData.class);
    }

    public static IAccountModule load(String contractAddress, Client client,
            CryptoKeyPair credential) {
        return new IAccountModule(contractAddress, client, credential);
    }

    public static IAccountModule deploy(Client client, CryptoKeyPair credential) throws
            ContractException {
        return deploy(IAccountModule.class, client, credential, getBinary(client.getCryptoSuite()), getABI(), null, null);
    }

    public static class IAccountModule.AccountData extends StaticStruct {
        public byte[] did;

        public String addr;

        public BigInteger status;

        public BigInteger accountType;

        public byte[] hash;

        public IAccountModule.AccountData(Bytes32 did, Address addr, Uint8 status,
                Uint8 accountType, Bytes32 hash) {
            super(did,addr,status,accountType,hash);
            this.did = did.getValue();
            this.addr = addr.getValue();
            this.status = status.getValue();
            this.accountType = accountType.getValue();
            this.hash = hash.getValue();
        }

        public IAccountModule.AccountData(byte[] did, String addr, BigInteger status,
                BigInteger accountType, byte[] hash) {
            super(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(did),new org.fisco.bcos.sdk.v3.codec.datatypes.Address(addr),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint8(status),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint8(accountType),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(hash));
            this.did = did;
            this.addr = addr;
            this.status = status;
            this.accountType = accountType;
            this.hash = hash;
        }
    }

    public static class AccountApprovedEventResponse {
        public TransactionReceipt.Logs log;

        public byte[] did;
    }

    public static class AccountDeniedEventResponse {
        public TransactionReceipt.Logs log;

        public byte[] did;
    }

    public static class AccountRegisteredEventResponse {
        public TransactionReceipt.Logs log;

        public byte[] did;

        public String addr;

        public BigInteger accountType;

        public byte[] hash;
    }
}
