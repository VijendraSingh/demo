package com.sample.model;

import java.io.IOException;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Customer Mapping class
 * @author Vijendra Singh
 * @version 1.0
 */
@Entity
@Table(name = "customer", uniqueConstraints = @UniqueConstraint(columnNames = {"roll_number"}))
public class Customer {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@JsonIgnore
	private int id;

	@Column(name = "customer_id", nullable = false)
	private Long customerId;

	@Column(name = "exam_instance_id")
	private int examInstanceId;

	@JsonIgnore
	@Column(name = "exam")
	private String examData;

	@Transient
	private Map<String, Object> exam;

	@Column(name = "roll_number")
	private String rollNumber; 

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Column(name = "marks")
	private double marks;

	@Column(name = "rank")
	private Integer rank;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getExamData() {
		return examData;
	}

	public void setExamData(String examData) {
		this.examData = examData;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getExam() {
		try {
			if(examData != null && !examData.isEmpty()){
				this.exam = new ObjectMapper().readValue(this.examData, Map.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exam;
	}

	public void setExam(Map<String,Object> exam) {
		try {
			if(!CollectionUtils.isEmpty(exam)){
				this.examData = new ObjectMapper().writeValueAsString(exam);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.exam = exam;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
	}

	public int getExamInstanceId() {
		return examInstanceId;
	}

	public void setExamInstanceId(int examInstanceId) {
		this.examInstanceId = examInstanceId;
	}
}
