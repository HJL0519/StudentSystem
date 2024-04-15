public class User {
    private String userName;
    private String password;
    private String personID;
    private String phoneNum;

    public User() {
    }

    public User(String userName, String password, String personID, String phoneNum) {
        this.userName = userName;
        this.password = password;
        this.personID = personID;
        this.phoneNum = phoneNum;
    }

    /**
     * 获取
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return personID
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * 设置
     * @param personID
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * 获取
     * @return phoneNum
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * 设置
     * @param phoneNum
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String toString() {
        return "User{userName = " + userName + ", password = " + password + ", personID = " + personID + ", phoneNum = " + phoneNum + "}";
    }
}
