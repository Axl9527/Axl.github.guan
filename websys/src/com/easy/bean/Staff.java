package com.easy.bean;


	import com.easy.util.ID;
	import com.easy.util.Table;
	@Table(tablename = "t_staff")
	public class Staff {
		@ID
		private Integer id;
		private String name;
		private Integer age;
		private double height;
		private String tel;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		public double getHeight() {
			return height;
		}
		public void setHeight(double height) {
			this.height = height;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		@Override
		public String toString() {
			return "Staff [id=" + id + ", name=" + name + ", age=" + age + ", height=" + height + ", tel=" + tel + "]";
		}
		public Staff(Integer id, String name, Integer age, double height, String tel) {
			super();
			this.id = id;
			this.name = name;
			this.age = age;
			this.height = height;
			this.tel = tel;
		}
		public Staff() {
			super();
		}
		
		

	}


