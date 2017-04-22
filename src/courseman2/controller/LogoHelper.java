package courseman2.controller;

import java.awt.*;

public class LogoHelper {
    private String LogoContext;
    private int miliSec;

    public LogoHelper(String logoContext, int miliSec) {
        LogoContext = logoContext;
        this.miliSec = miliSec;
    }

    public String getLogoContext() {
        return LogoContext;
    }

    public void setLogoContext(String logoContext) {
        LogoContext = logoContext;
    }

    public int getMiliSec() {
        return miliSec;
    }

    public void setMiliSec(int miliSec) {
        this.miliSec = miliSec;
    }

}
