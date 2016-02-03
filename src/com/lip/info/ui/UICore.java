/**
 *
 */
package com.lip.info.ui;

/**
 * @author Li Pei
 *
 * Andrew ID : lip
 */
public class UICore {

    private static final String DEFAULT_NAME = "SoC";
    private static final String DEFAULT_CODE = "18655";

    private String name = DEFAULT_NAME;
    private String code = DEFAULT_CODE;

    // set airline name and flight number
    public void setName(String comName, String comCode) {
        name = comName;
        code = comCode;
    }

    // get airline name
    public String getName() {
        return this.name;
    }

    // get airline code
    public String getCode() {
        return this.code;
    }
}
