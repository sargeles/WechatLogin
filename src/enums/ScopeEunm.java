package enums;

public enum ScopeEunm {
	snsapi_base("officialaccount","snsapi_base"),
	snsapi_userinfo("officialaccount","snsapi_userinfo"),
	snsapi_login("website","snsapi_login");
	
	private String state;
	
	private String scopeInfo;
	
	private ScopeEunm(String state, String scopeInfo) {
		this.state = state;
		this.scopeInfo = scopeInfo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getScopeInfo() {
		return scopeInfo;
	}

	public void setScopeInfo(String scopeInfo) {
		this.scopeInfo = scopeInfo;
	}
	
	public static ScopeEunm stateOf(String str){
		for(ScopeEunm state : values()){
			if(state.getState().equals(str) ){
				return state;
			}
		}
		return null;
	}
}
