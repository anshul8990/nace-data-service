package com.dbclm.nace.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString @EqualsAndHashCode
public class NaceDto {
	 
	 private String order;
	 private String level;
	 private String code;
	 private String parent;
	 private String description;
	 private String reference;
	 public NaceDto () {
	 }
	 public NaceDto(String order, String level, String code, String parent, String description, String reference) {
			this.order = order ;
			this.level = level;
			this.code = code;
			this.parent = parent;
			this.description = description;
            this.reference= reference;
		}
	 
	 public String getOrder() {
			return order;
		}

		public void setOrder(String order) {
			this.order = order;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getParent() {
			return parent;
		}

		public void setParent(String parent) {
			this.parent = parent;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getReference() {
			return reference;
		}

		public void setReference(String reference) {
			this.reference = reference;
		}
}
