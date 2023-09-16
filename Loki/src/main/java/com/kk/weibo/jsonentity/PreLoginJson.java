package com.kk.weibo.jsonentity;

import java.util.Date;

public class PreLoginJson {

	private Integer retcode;
	private Date servertime;
	private String pcid;
	private String nonce;
	private String pubkey;
	private Integer rsakv;
	private Integer exectime;

	public Integer getRetcode() {
		return retcode;
	}

	public void setRetcode(Integer retcode) {
		this.retcode = retcode;
	}

	public Date getServertime() {
		return servertime;
	}

	public void setServertime(Date servertime) {
		this.servertime = servertime;
	}

	public String getPcid() {
		return pcid;
	}

	public void setPcid(String pcid) {
		this.pcid = pcid;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getPubkey() {
		return pubkey;
	}

	public void setPubkey(String pubkey) {
		this.pubkey = pubkey;
	}

	public Integer getRsakv() {
		return rsakv;
	}

	public void setRsakv(Integer rsakv) {
		this.rsakv = rsakv;
	}

	public Integer getExectime() {
		return exectime;
	}

	public void setExectime(Integer exectime) {
		this.exectime = exectime;
	}

}
