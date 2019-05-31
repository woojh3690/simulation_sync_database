package com.einssnc.model;
// Generated 2019. 5. 31 ���� 9:03:40 by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Node generated by hbm2java
 */
@Entity
@Table(name = "node", catalog = "simulation")
public class Node implements java.io.Serializable {

	private String nodeId;
	private String nodeType;
	private String nodeName;
	private Character turnP;
	private String remark;
	private String userId;
	private Integer workstate;
	private Integer deptCode;
	private Integer stnlReg;
	private String tmpid;
	private String uploadId;
	private Set<Link> linksForFNode = new HashSet<Link>(0);
	private Set<Link> linksForTNode = new HashSet<Link>(0);
	private Set<Turninfo> turninfos = new HashSet<Turninfo>(0);

	public Node() {
	}

	public Node(String nodeId) {
		this.nodeId = nodeId;
	}

	public Node(String nodeId, String nodeType, String nodeName, Character turnP, String remark, String userId,
			Integer workstate, Integer deptCode, Integer stnlReg, String tmpid, String uploadId,
			Set<Link> linksForFNode, Set<Link> linksForTNode, Set<Turninfo> turninfos) {
		this.nodeId = nodeId;
		this.nodeType = nodeType;
		this.nodeName = nodeName;
		this.turnP = turnP;
		this.remark = remark;
		this.userId = userId;
		this.workstate = workstate;
		this.deptCode = deptCode;
		this.stnlReg = stnlReg;
		this.tmpid = tmpid;
		this.uploadId = uploadId;
		this.linksForFNode = linksForFNode;
		this.linksForTNode = linksForTNode;
		this.turninfos = turninfos;
	}

	@Id

	@Column(name = "node_id", unique = true, nullable = false, length = 10)
	public String getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	@Column(name = "node_type", length = 3)
	public String getNodeType() {
		return this.nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	@Column(name = "node_name", length = 100)
	public String getNodeName() {
		return this.nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	@Column(name = "turn_p", length = 1)
	public Character getTurnP() {
		return this.turnP;
	}

	public void setTurnP(Character turnP) {
		this.turnP = turnP;
	}

	@Column(name = "remark", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "user_id", length = 100)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "workstate")
	public Integer getWorkstate() {
		return this.workstate;
	}

	public void setWorkstate(Integer workstate) {
		this.workstate = workstate;
	}

	@Column(name = "dept_code")
	public Integer getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(Integer deptCode) {
		this.deptCode = deptCode;
	}

	@Column(name = "stnl_reg")
	public Integer getStnlReg() {
		return this.stnlReg;
	}

	public void setStnlReg(Integer stnlReg) {
		this.stnlReg = stnlReg;
	}

	@Column(name = "tmpid", length = 100)
	public String getTmpid() {
		return this.tmpid;
	}

	public void setTmpid(String tmpid) {
		this.tmpid = tmpid;
	}

	@Column(name = "upload_id", length = 100)
	public String getUploadId() {
		return this.uploadId;
	}

	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nodeByFNode")
	public Set<Link> getLinksForFNode() {
		return this.linksForFNode;
	}

	public void setLinksForFNode(Set<Link> linksForFNode) {
		this.linksForFNode = linksForFNode;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nodeByTNode")
	public Set<Link> getLinksForTNode() {
		return this.linksForTNode;
	}

	public void setLinksForTNode(Set<Link> linksForTNode) {
		this.linksForTNode = linksForTNode;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "node")
	public Set<Turninfo> getTurninfos() {
		return this.turninfos;
	}

	public void setTurninfos(Set<Turninfo> turninfos) {
		this.turninfos = turninfos;
	}

}
