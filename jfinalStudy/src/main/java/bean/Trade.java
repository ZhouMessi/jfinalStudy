package bean;

import com.jfinal.plugin.activerecord.Model;
import org.apache.log4j.Logger;

public class Trade extends Model<Trade> {

    private static final long serialVersionUID = 1L;

    public static final Trade dao = new Trade();

    private static Logger L = Logger.getLogger(Trade.class);
}
