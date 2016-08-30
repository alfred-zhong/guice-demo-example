import com.snowinpluto.demo.AppServletContextListener
import com.snowinpluto.demo.entity.User
import com.snowinpluto.demo.mybatis.page.Page
import com.snowinpluto.demo.service.UserService
import org.crsh.cli.Argument
import org.crsh.cli.Command
import org.crsh.cli.Required
import org.crsh.cli.Usage
import org.crsh.command.CRaSHCommand
import org.crsh.text.Color
import org.crsh.text.Style

@Usage("user command")
class user extends CRaSHCommand {

    @Usage("print all users")
    @Command
    Object printAll() {
        def singleton = context.attributes.beans["com.snowinpluto.demo.service.UserService"];
        if (singleton != null) {
            Page<User> page = singleton.invokeMethod("findUserPage", [1, Integer.MAX_VALUE] as Object[]);
            if (page != null) {
                out.print("ID\t\t", Style.style(true, false, false, Color.blue, Color.black));
                out.print("NAME\t\t", Style.style(true, false, false, Color.blue, Color.black));
                out.print("AGE\t\t", Style.style(true, false, false, Color.blue, Color.black));
                out.println();

                for (User user : page.getResult()) {
                    out.print(user.getId() + "\t\t", Color.white);
                    out.print(user.getName() + "\t\t", Color.white);
                    out.print(user.getAge() + "\t\t", Color.white);
                    out.println();
                }

                return "";
            }
        }

        return "No such type";
    }

    @Usage("add a user")
    @Command
    void add(@Usage("name of the user") @Argument @Required String name,
             @Usage("age of the user") @Argument @Required int age) {
        /*def singleton = context.attributes.beans["com.snowinpluto.demo.service.UserService"];
        if (singleton != null) {
            User user = new User();
            user.setName(name);
            user.setAge(age);

            User newUser = singleton.invokeMethod("add", [user] as Object[]);
            if (newUser != null) {
                out.println("User add Success", Color.green);
            }
            else {
                out.println("Fail, name already exists!", Color.red);
            }
        }*/

        UserService userService = AppServletContextListener.injector.getInstance(UserService.class);
        if (userService != null) {
            User user = new User();
            user.setName(name);
            user.setAge(age);

            try {
                userService.add(user);
                out.println("User add Success", Color.green);
            } catch (Exception e) {
                out.println("Fail, name already exists!", Color.red);
            }
        }
    }
}