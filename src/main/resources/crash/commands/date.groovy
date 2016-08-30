import com.snowinpluto.demo.utils.DateUtil
import org.crsh.cli.Command
import org.crsh.cli.Option
import org.crsh.cli.Usage
import org.crsh.command.CRaSHCommand

@Usage("display server date")
class date extends CRaSHCommand {

    @Usage("show server date")
    @Command
    Object show() {
        return DateUtil.formatDateYMDHMS(new Date());
    }

    @Usage("format date")
    @Command
    Object format(@Usage("the format of the date") @Option(names = ["f", "format"]) String format) {
        if (format != null) {
            return DateUtil.formatDate(format, new Date());
        }

        return DateUtil.formatDateYMDHMS(new Date());
    }
}