package cz.muni.fi.pa165.project.dto;

public class PersonAuthenticateDTO
{
    private Long userId;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private String password;

    public Long getPersonId()
    {
        return userId;
    }

    public void setPersonId(Long userId)
    {
        this.userId = userId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
