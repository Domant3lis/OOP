package items;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Contact extends Note
{
    private String []presets = {"Full name", "Phone number", "Email"};
    private HashMap<String, String> fields = new LinkedHashMap<String, String>();
    private LocalDateTime birthday;

    public Contact(String title, String desc, String fullName, String phoneNumber, String email, LocalDateTime birthday)
    {
        super(title, desc);
        setFullName(fullName);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        this.birthday = birthday;
    }

    @Override
    public String toString()
    {
        String ret = super.toString();
        ret += "\nEmail: " + getEmail();
        ret = emailIsValid() ? ret.concat(" [VALID]") : ret.concat(" [NOT VALID]");


        for(String key : fields.keySet())
        {
            ret = key == "Email" ? ret : ret.concat("\n" + key + ": " + fields.get(key));
        }

        return ret;
    }

    @Override
    public boolean contains(String match)
    {
        if (super.contains(match))
            return true;

        if (this.birthday != null && this.birthday.toString().contains(match))
            return true;
        
        return this.fields.values().stream().anyMatch( v -> v.contains(match) );
    }

    @Override
    public boolean contains(Pattern regex)
    {
        if (super.contains(regex))
            return true;
        
        if (this.birthday != null && regex.matcher(this.birthday.toString()).find())
            return true;
        
        return this.fields.values().stream().anyMatch( v -> regex.matcher(v).find());
    }

    public boolean hasField(String key)
        { return fields.containsKey(key); }

    public boolean emailIsValid()
    {
        final Pattern regex = Pattern.compile("^[^\\s@]+@([^\\s@.,]+\\.)+[^\\s@.,]{2,}");

        if(regex.matcher(getEmail()).find())
            return true;

        return false;
    }

    public void writeEmail()
    {
        Scanner scanner = new Scanner(System.in);
        String appendix = "mailto:" + getEmail();

        System.out.println("Write Subject line: ");
        String subjectLine = scanner.nextLine();
        
        System.out.println("Write Body line: ");
        String body = scanner.nextLine();
        System.out.println("Use this link in your browser: " + appendix + "?subject=" + subjectLine + "&body=" + body);
    }
    
    // Only getters and setters below
    public LocalDateTime getBirthday() { return this.birthday; }
    public String getFullName() { return fields.get(presets[0]); }
    public String getPhoneNumber() { return fields.get(presets[1]); }
    public String getEmail() { return fields.get(presets[2]); }
    public String getCustom(String key) {return fields.get(key); }
    
    public void setBirthday(LocalDateTime birthday) { this.birthday = birthday; }
    public void setFullName(String fullName) { this.fields.put(presets[0], fullName); }
    public void setPhoneNumber(String phoneNumber) { this.fields.put(presets[1], phoneNumber); }
    public void setEmail(String email) { this.fields.put(presets[2], email); }
    public void setCustom(String key, String value) { this.fields.put(key, value); }
}