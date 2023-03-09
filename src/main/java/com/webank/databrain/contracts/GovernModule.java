package com.webank.databrain.contracts;

import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.codec.datatypes.*;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint8;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.v3.contract.Contract;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.CryptoType;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public class GovernModule extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50610f48806100206000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c806350eb93211161005b57806350eb9321146100cd578063a5686826146100ee578063a69beaba14610101578063eecdac881461011457600080fd5b8063295a52121461008257806337376ca8146100a55780633c4a25d0146100ba575b600080fd5b60025461008f9060ff1681565b60405161009c9190610c88565b60405180910390f35b6100b86100b3366004610cb0565b610127565b005b6100b86100c8366004610ce5565b6101f9565b6100e06100db366004610d07565b61036d565b60405190815260200161009c565b6100b86100fc366004610d8e565b610402565b6100b861010f366004610cb0565b6104d0565b6100b8610122366004610ce5565b61084d565b61013033610969565b60008181526003602052604090206001600482810154610100900460ff169081111561015e5761015e610c72565b146101b05760405162461bcd60e51b815260206004820152601a60248201527f496e76616c6964207472616e73616374696f6e2073746174757300000000000060448201526064015b60405180910390fd5b60048101805461ff0019166104001790556040518281527f4973d6156c5324170079a1bf6e22313bedcf5baf0b036efbd1a7e6e9ec436960906020015b60405180910390a15050565b3330141561029f576001600160a01b03811660009081526020819052604090205460ff16156102655760405162461bcd60e51b815260206004820152601860248201527711dbdd995c9b9bdc88185b1c9958591e48195e1a5cdd195960421b60448201526064016101a7565b6001600160a01b0381166000908152602081905260408120805460ff1916600190811790915580549161029783610dc5565b919050555050565b6102a833610969565b600060025460ff1660018111156102c1576102c1610c72565b141561032b576001600160a01b03811660009081526020819052604090205460ff16156102655760405162461bcd60e51b815260206004820152601860248201527711dbdd995c9b9bdc88185b1c9958591e48195e1a5cdd195960421b60448201526064016101a7565b600160025460ff16600181111561034457610344610c72565b141561036a57336000818152600460205260408120546103689291309190366109c7565b505b50565b3360009081526004602052604081205484908082146103c45760405162461bcd60e51b8152602060048201526013602482015272417574683a20496e76616c6964206e6f6e636560681b60448201526064016101a7565b6103cd33610969565b6103da33888888886109c7565b92506103e7816001610de0565b33600090815260046020526040902055509095945050505050565b333014156104965780600181111561041c5761041c610c72565b60025460ff16600181111561043357610433610c72565b14156104705760405162461bcd60e51b815260206004820152600c60248201526b496e76616c6964206d6f646560a01b60448201526064016101a7565b6002805482919060ff19166001838181111561048e5761048e610c72565b021790555050565b61049f33610969565b600060025460ff1660018111156104b8576104b8610c72565b141561032b5780600181111561041c5761041c610c72565b6104d933610969565b6000818152600360208181526040808420815160c08101835281546001600160a01b03908116825260018301541693810193909352600281015491830191909152918201805491929160608401919061053190610df8565b80601f016020809104026020016040519081016040528092919081815260200182805461055d90610df8565b80156105aa5780601f1061057f576101008083540402835291602001916105aa565b820191906000526020600020905b81548152906001019060200180831161058d57829003601f168201915b505050918352505060048281015460ff808216602085015260409093019261010090910416908111156105df576105df610c72565b60048111156105f0576105f0610c72565b905250905060018160a00151600481111561060d5761060d610c72565b1461065a5760405162461bcd60e51b815260206004820152601a60248201527f496e76616c6964207472616e73616374696f6e2073746174757300000000000060448201526064016101a7565b6080810180519061066a82610e33565b60ff169052506001546080820151610683906002610e53565b60ff161061077057602081015160608201516040516000906001600160a01b038416906106b1908490610e7c565b6000604051808303816000865af19150503d80600081146106ee576040519150601f19603f3d011682016040523d82523d6000602084013e6106f3565b606091505b5050905080610703576003610706565b60025b8460a00190600481111561071c5761071c610c72565b9081600481111561072f5761072f610c72565b9052506040805186815282151560208201527f3b35e7d1d39c6fa4f9cc7acf876c5c3a01117f7b10f456540321277c95fa07a7910160405180910390a15050505b600082815260036020818152604092839020845181546001600160a01b039182166001600160a01b031991821617835583870151600184018054919093169116179055928401516002840155606084015180518594936107d593908501920190610bd9565b5060808201516004808301805460ff90931660ff1984168117825560a086015193919261ffff19909216179061010090849081111561081657610816610c72565b0217905550506040518381527f4973d6156c5324170079a1bf6e22313bedcf5baf0b036efbd1a7e6e9ec43696091506020016101ed565b333014156108e2576001600160a01b03811660009081526020819052604090205460ff166108b45760405162461bcd60e51b815260206004820152601460248201527311dbdd995c9b9bdc881b9bdd08195e1a5cdd195960621b60448201526064016101a7565b6001600160a01b0381166000908152602081905260408120805460ff19169055600180549161029783610eb7565b6108eb33610969565b600060025460ff16600181111561090457610904610c72565b141561032b576001600160a01b03811660009081526020819052604090205460ff166108b45760405162461bcd60e51b815260206004820152601460248201527311dbdd995c9b9bdc881b9bdd08195e1a5cdd195960621b60448201526064016101a7565b6001600160a01b03811660009081526020819052604090205460ff1661036a5760405162461bcd60e51b815260206004820152601360248201527223b7bb1d1024b73b30b634b21031b0b63632b960691b60448201526064016101a7565b600085858585856040516020016109e2959493929190610ece565b60408051601f1981840301815291905280516020909101209050600080828152600360205260409020600490810154610100900460ff1690811115610a2957610a29610c72565b14610a765760405162461bcd60e51b815260206004820152601960248201527f5472616e73616374696f6e20616c72656164792065786973740000000000000060448201526064016101a7565b60006040518060c00160405280886001600160a01b03168152602001876001600160a01b0316815260200186815260200185858080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920182905250938552505050602082015260400160019052600083815260036020818152604092839020845181546001600160a01b039182166001600160a01b031991821617835583870151600184018054919093169116179055928401516002840155606084015180519495508594610b54938501929190910190610bd9565b5060808201516004808301805460ff90931660ff1984168117825560a086015193919261ffff199092161790610100908490811115610b9557610b95610c72565b0217905550506040518381527f2d231481d8c1af3958a30a257e93e0df6b089bc07ebedc0a86e7355d1aaf1338915060200160405180910390a15095945050505050565b828054610be590610df8565b90600052602060002090601f016020900481019282610c075760008555610c4d565b82601f10610c2057805160ff1916838001178555610c4d565b82800160010185558215610c4d579182015b82811115610c4d578251825591602001919060010190610c32565b50610c59929150610c5d565b5090565b5b80821115610c595760008155600101610c5e565b634e487b7160e01b600052602160045260246000fd5b6020810160028310610caa57634e487b7160e01b600052602160045260246000fd5b91905290565b600060208284031215610cc257600080fd5b5035919050565b80356001600160a01b0381168114610ce057600080fd5b919050565b600060208284031215610cf757600080fd5b610d0082610cc9565b9392505050565b60008060008060608587031215610d1d57600080fd5b610d2685610cc9565b935060208501359250604085013567ffffffffffffffff80821115610d4a57600080fd5b818701915087601f830112610d5e57600080fd5b813581811115610d6d57600080fd5b886020828501011115610d7f57600080fd5b95989497505060200194505050565b600060208284031215610da057600080fd5b813560028110610d0057600080fd5b634e487b7160e01b600052601160045260246000fd5b6000600019821415610dd957610dd9610daf565b5060010190565b60008219821115610df357610df3610daf565b500190565b600181811c90821680610e0c57607f821691505b60208210811415610e2d57634e487b7160e01b600052602260045260246000fd5b50919050565b600060ff821660ff811415610e4a57610e4a610daf565b60010192915050565b600060ff821660ff84168160ff0481118215151615610e7457610e74610daf565b029392505050565b6000825160005b81811015610e9d5760208186018101518583015201610e83565b81811115610eac576000828501525b509190910192915050565b600081610ec657610ec6610daf565b506000190190565b60006bffffffffffffffffffffffff19808860601b168352808760601b1660148401525084602883015282846048840137506000910160480190815294935050505056fea264697066735822122045e4deb8df60ca1e8550c5d7aac8e85f0c98c475752257a128e4ab6acc68d07964736f6c634300080b0033"};

    public static final String BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b50610f4e806100206000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c8063609555081161005b57806360955508146100d757806369c4170d146100ea5780638ef705c0146100fd578063af47703a1461011057600080fd5b80630d6709a91461008257806339299d68146100975780633d8ee45c146100bd575b600080fd5b610095610090366004610c94565b610123565b005b6100aa6100a5366004610cb6565b61029e565b6040519081526020015b60405180910390f35b6002546100ca9060ff1681565b6040516100b49190610d53565b6100956100e5366004610d7b565b610334565b6100956100f8366004610d94565b6106ba565b61009561010b366004610c94565b610789565b61009561011e366004610d7b565b6108a7565b333014156101cf576001600160a01b03811660009081526020819052604090205460ff161561019557604051636381e58960e11b815260206004820152601860248201527711dbdd995c9b9bdc88185b1c9958591e48195e1a5cdd195960421b60448201526064015b60405180910390fd5b6001600160a01b0381166000908152602081905260408120805460ff191660019081179091558054916101c783610dcb565b919050555050565b6101d83361096d565b600060025460ff1660018111156101f1576101f1610d3d565b141561025c576001600160a01b03811660009081526020819052604090205460ff161561019557604051636381e58960e11b815260206004820152601860248201527711dbdd995c9b9bdc88185b1c9958591e48195e1a5cdd195960421b604482015260640161018c565b600160025460ff16600181111561027557610275610d3d565b141561029b57336000818152600460205260408120546102999291309190366109cc565b505b50565b3360009081526004602052604081205484908082146102f657604051636381e58960e11b8152602060048201526013602482015272417574683a20496e76616c6964206e6f6e636560681b604482015260640161018c565b6102ff3361096d565b61030c33888888886109cc565b9250610319816001610de6565b33600090815260046020526040902055509095945050505050565b61033d3361096d565b6000818152600360208181526040808420815160c08101835281546001600160a01b03908116825260018301541693810193909352600281015491830191909152918201805491929160608401919061039590610dfe565b80601f01602080910402602001604051908101604052809291908181526020018280546103c190610dfe565b801561040e5780601f106103e35761010080835404028352916020019161040e565b820191906000526020600020905b8154815290600101906020018083116103f157829003601f168201915b505050918352505060048281015460ff8082166020850152604090930192610100909104169081111561044357610443610d3d565b600481111561045457610454610d3d565b905250905060018160a00151600481111561047157610471610d3d565b146104bf57604051636381e58960e11b815260206004820152601a60248201527f496e76616c6964207472616e73616374696f6e20737461747573000000000000604482015260640161018c565b608081018051906104cf82610e39565b60ff1690525060015460808201516104e8906002610e59565b60ff16106105d557602081015160608201516040516000906001600160a01b03841690610516908490610e82565b6000604051808303816000865af19150503d8060008114610553576040519150601f19603f3d011682016040523d82523d6000602084013e610558565b606091505b505090508061056857600361056b565b60025b8460a00190600481111561058157610581610d3d565b9081600481111561059457610594610d3d565b9052506040805186815282151560208201527f7781cd6bd4cd7c762b453c43635aef1033a0d5de14ea15db6e9d641398d31102910160405180910390a15050505b600082815260036020818152604092839020845181546001600160a01b039182166001600160a01b0319918216178355838701516001840180549190931691161790559284015160028401556060840151805185949361063a93908501920190610bdf565b5060808201516004808301805460ff90931660ff1984168117825560a086015193919261ffff19909216179061010090849081111561067b5761067b610d3d565b0217905550506040518381527f5058486f68922f5def2ddcf51515b2db34dd9fb76d56609ba4375a8271475aef91506020015b60405180910390a15050565b3330141561074f578060018111156106d4576106d4610d3d565b60025460ff1660018111156106eb576106eb610d3d565b141561072957604051636381e58960e11b815260206004820152600c60248201526b496e76616c6964206d6f646560a01b604482015260640161018c565b6002805482919060ff19166001838181111561074757610747610d3d565b021790555050565b6107583361096d565b600060025460ff16600181111561077157610771610d3d565b141561025c578060018111156106d4576106d4610d3d565b3330141561081f576001600160a01b03811660009081526020819052604090205460ff166107f157604051636381e58960e11b815260206004820152601460248201527311dbdd995c9b9bdc881b9bdd08195e1a5cdd195960621b604482015260640161018c565b6001600160a01b0381166000908152602081905260408120805460ff1916905560018054916101c783610ebd565b6108283361096d565b600060025460ff16600181111561084157610841610d3d565b141561025c576001600160a01b03811660009081526020819052604090205460ff166107f157604051636381e58960e11b815260206004820152601460248201527311dbdd995c9b9bdc881b9bdd08195e1a5cdd195960621b604482015260640161018c565b6108b03361096d565b60008181526003602052604090206001600482810154610100900460ff16908111156108de576108de610d3d565b1461092c57604051636381e58960e11b815260206004820152601a60248201527f496e76616c6964207472616e73616374696f6e20737461747573000000000000604482015260640161018c565b60048101805461ff0019166104001790556040518281527f5058486f68922f5def2ddcf51515b2db34dd9fb76d56609ba4375a8271475aef906020016106ae565b6001600160a01b03811660009081526020819052604090205460ff1661029b57604051636381e58960e11b815260206004820152601360248201527223b7bb1d1024b73b30b634b21031b0b63632b960691b604482015260640161018c565b600085858585856040516020016109e7959493929190610ed4565b60408051601f1981840301815291905280516020909101209050600080828152600360205260409020600490810154610100900460ff1690811115610a2e57610a2e610d3d565b14610a7c57604051636381e58960e11b815260206004820152601960248201527f5472616e73616374696f6e20616c726561647920657869737400000000000000604482015260640161018c565b60006040518060c00160405280886001600160a01b03168152602001876001600160a01b0316815260200186815260200185858080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920182905250938552505050602082015260400160019052600083815260036020818152604092839020845181546001600160a01b039182166001600160a01b031991821617835583870151600184018054919093169116179055928401516002840155606084015180519495508594610b5a938501929190910190610bdf565b5060808201516004808301805460ff90931660ff1984168117825560a086015193919261ffff199092161790610100908490811115610b9b57610b9b610d3d565b0217905550506040518381527f24ba2a097b8583ceb85c849bc3906addc9c36dddfa40b96449fb65f70b549aca915060200160405180910390a15095945050505050565b828054610beb90610dfe565b90600052602060002090601f016020900481019282610c0d5760008555610c53565b82601f10610c2657805160ff1916838001178555610c53565b82800160010185558215610c53579182015b82811115610c53578251825591602001919060010190610c38565b50610c5f929150610c63565b5090565b5b80821115610c5f5760008155600101610c64565b80356001600160a01b0381168114610c8f57600080fd5b919050565b600060208284031215610ca657600080fd5b610caf82610c78565b9392505050565b60008060008060608587031215610ccc57600080fd5b610cd585610c78565b935060208501359250604085013567ffffffffffffffff80821115610cf957600080fd5b818701915087601f830112610d0d57600080fd5b813581811115610d1c57600080fd5b886020828501011115610d2e57600080fd5b95989497505060200194505050565b63b95aa35560e01b600052602160045260246000fd5b6020810160028310610d755763b95aa35560e01b600052602160045260246000fd5b91905290565b600060208284031215610d8d57600080fd5b5035919050565b600060208284031215610da657600080fd5b813560028110610caf57600080fd5b63b95aa35560e01b600052601160045260246000fd5b6000600019821415610ddf57610ddf610db5565b5060010190565b60008219821115610df957610df9610db5565b500190565b600181811c90821680610e1257607f821691505b60208210811415610e335763b95aa35560e01b600052602260045260246000fd5b50919050565b600060ff821660ff811415610e5057610e50610db5565b60010192915050565b600060ff821660ff84168160ff0481118215151615610e7a57610e7a610db5565b029392505050565b6000825160005b81811015610ea35760208186018101518583015201610e89565b81811115610eb2576000828501525b509190910192915050565b600081610ecc57610ecc610db5565b506000190190565b60006bffffffffffffffffffffffff19808860601b168352808760601b1660148401525084602883015282846048840137506000910160480190815294935050505056fea2646970667358221220a1c26655a1fed7c98d1b96a793295549158a068859a98775dfddbf43b6f8900164736f6c634300080b0033"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"bytes32\",\"name\":\"proposalId\",\"type\":\"bytes32\"}],\"name\":\"CancelProposal\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"bytes32\",\"name\":\"proposalId\",\"type\":\"bytes32\"}],\"name\":\"CreateProposal\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"bytes32\",\"name\":\"proposalId\",\"type\":\"bytes32\"},{\"indexed\":false,\"internalType\":\"bool\",\"name\":\"success\",\"type\":\"bool\"}],\"name\":\"ExecuteProposal\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"bytes32\",\"name\":\"proposalId\",\"type\":\"bytes32\"}],\"name\":\"VoteProposal\",\"type\":\"event\"},{\"conflictFields\":[{\"kind\":0},{\"kind\":2,\"slot\":0,\"value\":[0]},{\"kind\":2,\"slot\":4,\"value\":[0]},{\"kind\":4,\"value\":[1]},{\"kind\":4,\"value\":[2]}],\"inputs\":[{\"internalType\":\"address\",\"name\":\"governor\",\"type\":\"address\"}],\"name\":\"addGovernor\",\"outputs\":[],\"selector\":[1011492304,224856489],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":2,\"slot\":0,\"value\":[0]},{\"kind\":3,\"slot\":3,\"value\":[0]}],\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"proposalId\",\"type\":\"bytes32\"}],\"name\":\"cancelProposal\",\"outputs\":[],\"selector\":[926379176,2940694586],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0},{\"kind\":2,\"slot\":0,\"value\":[0]},{\"kind\":2,\"slot\":4,\"value\":[0]},{\"kind\":4,\"value\":[2]}],\"inputs\":[{\"internalType\":\"enum GovernModule.GovernMode\",\"name\":\"newMode\",\"type\":\"uint8\"}],\"name\":\"changeMode\",\"outputs\":[],\"selector\":[2775083046,1774458637],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0},{\"kind\":2,\"slot\":0,\"value\":[0]},{\"kind\":2,\"slot\":4,\"value\":[0]}],\"inputs\":[{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"nonce\",\"type\":\"uint256\"},{\"internalType\":\"bytes\",\"name\":\"data\",\"type\":\"bytes\"}],\"name\":\"createProposal\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"proposalId\",\"type\":\"bytes32\"}],\"selector\":[1357615905,959028584],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":4,\"value\":[2]}],\"inputs\":[],\"name\":\"mode\",\"outputs\":[{\"internalType\":\"enum GovernModule.GovernMode\",\"name\":\"\",\"type\":\"uint8\"}],\"selector\":[693785106,1032774748],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0},{\"kind\":2,\"slot\":0,\"value\":[0]},{\"kind\":2,\"slot\":4,\"value\":[0]},{\"kind\":4,\"value\":[1]},{\"kind\":4,\"value\":[2]}],\"inputs\":[{\"internalType\":\"address\",\"name\":\"governor\",\"type\":\"address\"}],\"name\":\"removeGovernor\",\"outputs\":[],\"selector\":[4006456456,2398553536],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0}],\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"proposalId\",\"type\":\"bytes32\"}],\"name\":\"vote\",\"outputs\":[],\"selector\":[2795236026,1620399368],\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_ADDGOVERNOR = "addGovernor";

    public static final String FUNC_CANCELPROPOSAL = "cancelProposal";

    public static final String FUNC_CHANGEMODE = "changeMode";

    public static final String FUNC_CREATEPROPOSAL = "createProposal";

    public static final String FUNC_MODE = "mode";

    public static final String FUNC_REMOVEGOVERNOR = "removeGovernor";

    public static final String FUNC_VOTE = "vote";

    public static final Event CANCELPROPOSAL_EVENT = new Event("CancelProposal", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
    ;

    public static final Event CREATEPROPOSAL_EVENT = new Event("CreateProposal", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
    ;

    public static final Event EXECUTEPROPOSAL_EVENT = new Event("ExecuteProposal", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bool>() {}));
    ;

    public static final Event VOTEPROPOSAL_EVENT = new Event("VoteProposal", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
    ;

    protected GovernModule(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static String getABI() {
        return ABI;
    }

    public List<CancelProposalEventResponse> getCancelProposalEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CANCELPROPOSAL_EVENT, transactionReceipt);
        ArrayList<CancelProposalEventResponse> responses = new ArrayList<CancelProposalEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CancelProposalEventResponse typedResponse = new CancelProposalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<CreateProposalEventResponse> getCreateProposalEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CREATEPROPOSAL_EVENT, transactionReceipt);
        ArrayList<CreateProposalEventResponse> responses = new ArrayList<CreateProposalEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CreateProposalEventResponse typedResponse = new CreateProposalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<ExecuteProposalEventResponse> getExecuteProposalEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(EXECUTEPROPOSAL_EVENT, transactionReceipt);
        ArrayList<ExecuteProposalEventResponse> responses = new ArrayList<ExecuteProposalEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ExecuteProposalEventResponse typedResponse = new ExecuteProposalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.success = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<VoteProposalEventResponse> getVoteProposalEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VOTEPROPOSAL_EVENT, transactionReceipt);
        ArrayList<VoteProposalEventResponse> responses = new ArrayList<VoteProposalEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            VoteProposalEventResponse typedResponse = new VoteProposalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public TransactionReceipt addGovernor(String governor) {
        final Function function = new Function(
                FUNC_ADDGOVERNOR, 
                Arrays.<Type>asList(new Address(governor)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String addGovernor(String governor, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ADDGOVERNOR, 
                Arrays.<Type>asList(new Address(governor)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForAddGovernor(String governor) {
        final Function function = new Function(
                FUNC_ADDGOVERNOR, 
                Arrays.<Type>asList(new Address(governor)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public Tuple1<String> getAddGovernorInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ADDGOVERNOR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public TransactionReceipt cancelProposal(byte[] proposalId) {
        final Function function = new Function(
                FUNC_CANCELPROPOSAL, 
                Arrays.<Type>asList(new Bytes32(proposalId)),
                Collections.<TypeReference<?>>emptyList(), 4);
        return executeTransaction(function);
    }

    public String cancelProposal(byte[] proposalId, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CANCELPROPOSAL, 
                Arrays.<Type>asList(new Bytes32(proposalId)),
                Collections.<TypeReference<?>>emptyList(), 4);
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForCancelProposal(byte[] proposalId) {
        final Function function = new Function(
                FUNC_CANCELPROPOSAL, 
                Arrays.<Type>asList(new Bytes32(proposalId)),
                Collections.<TypeReference<?>>emptyList(), 4);
        return createSignedTransaction(function);
    }

    public Tuple1<byte[]> getCancelProposalInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CANCELPROPOSAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<byte[]>(

                (byte[]) results.get(0).getValue()
                );
    }

    public TransactionReceipt changeMode(BigInteger newMode) {
        final Function function = new Function(
                FUNC_CHANGEMODE, 
                Arrays.<Type>asList(new Uint8(newMode)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String changeMode(BigInteger newMode, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CHANGEMODE, 
                Arrays.<Type>asList(new Uint8(newMode)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForChangeMode(BigInteger newMode) {
        final Function function = new Function(
                FUNC_CHANGEMODE, 
                Arrays.<Type>asList(new Uint8(newMode)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getChangeModeInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CHANGEMODE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public TransactionReceipt createProposal(String to, BigInteger nonce, byte[] data) {
        final Function function = new Function(
                FUNC_CREATEPROPOSAL, 
                Arrays.<Type>asList(new Address(to),
                new Uint256(nonce),
                new DynamicBytes(data)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String createProposal(String to, BigInteger nonce, byte[] data,
            TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CREATEPROPOSAL, 
                Arrays.<Type>asList(new Address(to),
                new Uint256(nonce),
                new DynamicBytes(data)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForCreateProposal(String to, BigInteger nonce, byte[] data) {
        final Function function = new Function(
                FUNC_CREATEPROPOSAL, 
                Arrays.<Type>asList(new Address(to),
                new Uint256(nonce),
                new DynamicBytes(data)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public Tuple3<String, BigInteger, byte[]> getCreateProposalInput(
            TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATEPROPOSAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<String, BigInteger, byte[]>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (byte[]) results.get(2).getValue()
                );
    }

    public Tuple1<byte[]> getCreateProposalOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CREATEPROPOSAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<byte[]>(

                (byte[]) results.get(0).getValue()
                );
    }

    public BigInteger mode() throws ContractException {
        final Function function = new Function(FUNC_MODE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public TransactionReceipt removeGovernor(String governor) {
        final Function function = new Function(
                FUNC_REMOVEGOVERNOR, 
                Arrays.<Type>asList(new Address(governor)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String removeGovernor(String governor, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_REMOVEGOVERNOR, 
                Arrays.<Type>asList(new Address(governor)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForRemoveGovernor(String governor) {
        final Function function = new Function(
                FUNC_REMOVEGOVERNOR, 
                Arrays.<Type>asList(new Address(governor)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public Tuple1<String> getRemoveGovernorInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_REMOVEGOVERNOR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public TransactionReceipt vote(byte[] proposalId) {
        final Function function = new Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new Bytes32(proposalId)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String vote(byte[] proposalId, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new Bytes32(proposalId)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForVote(byte[] proposalId) {
        final Function function = new Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new Bytes32(proposalId)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public Tuple1<byte[]> getVoteInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_VOTE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<byte[]>(

                (byte[]) results.get(0).getValue()
                );
    }

    public static GovernModule load(String contractAddress, Client client,
            CryptoKeyPair credential) {
        return new GovernModule(contractAddress, client, credential);
    }

    public static GovernModule deploy(Client client, CryptoKeyPair credential) throws
            ContractException {
        return deploy(GovernModule.class, client, credential, getBinary(client.getCryptoSuite()), getABI(), null, null);
    }

    public static class CancelProposalEventResponse {
        public TransactionReceipt.Logs log;

        public byte[] proposalId;
    }

    public static class CreateProposalEventResponse {
        public TransactionReceipt.Logs log;

        public byte[] proposalId;
    }

    public static class ExecuteProposalEventResponse {
        public TransactionReceipt.Logs log;

        public byte[] proposalId;

        public Boolean success;
    }

    public static class VoteProposalEventResponse {
        public TransactionReceipt.Logs log;

        public byte[] proposalId;
    }
}
