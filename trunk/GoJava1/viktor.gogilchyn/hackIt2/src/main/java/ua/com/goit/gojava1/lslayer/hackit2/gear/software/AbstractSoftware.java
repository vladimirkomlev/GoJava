package ua.com.goit.gojava1.lslayer.hackit2.gear.software;

import ua.com.goit.gojava1.lslayer.hackit2.gear.AbstractUtility;


public class AbstractSoftware extends AbstractUtility implements Software {
    public AbstractSoftware(String name) {
        super(name);
        this.version = 1;
    }

    private int version;

    @Override
    public int getVersion() {
        return this.version;
    }
}
