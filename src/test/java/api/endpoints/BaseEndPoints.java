package api.endpoints;

import java.util.ResourceBundle;

public abstract class BaseEndPoints {

    protected static final String BEARER_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMzBmM2" +
            "U5N2VjOGRiNWRkNmE2NDhjNGRmOTAzZjkyMTJiOGIzZmQyMGFiNGQzYzc3YzZiYzFkODM3YjEzNWY3MWU3Y2ZhZTEzZjMyYmQ4MDYiLC" +
            "JpYXQiOjE3MTYxNDkxOTYuNzA5MjkxLCJuYmYiOjE3MTYxNDkxOTYuNzA5MjkyLCJleHAiOjE3NDc2ODUxOTYuNjk3MDA2LCJzdWIiOi" +
            "IxIiwic2NvcGVzIjpbXX0.2JPuG_Jvm7BKgaASRKA2sWFATpzomE4ZAPWmJxMcNImve5BCs08SwxJxW-4PEB2mi1vDxLkpfz49Hz2kR3" +
            "M6IwblgxyzmEg-BLCmSS8mmUBz3NV3BM4NfdNvQTO_Lg59acJJQd4g3pcMatgHzyLF1rTSg7bZ8byeLh-b84DMXVtqmSjeIX6hsNpqwC" +
            "tgVeYmlkMwq7nUZNtTBgIB02Dgv2iABeQjlr-a5B4tzH4IAJ3w6gEbFolApO1jheDz9D7daycJfpTzc_phHMtqzfh8mE3oQpYy7JBRng" +
            "E-Xi-UJbsKg88Qkst0VAhfF7dRNDHWG_rIBN64ANl_X5JzXR_11jUYvAzX1WiII0yyOTGrQTC0VMeg_vZLTuvTOBckcyySsjj0fUMdbE" +
            "Zi-6UHjT3FFe2h03bY5SgjLbNdHqPEMgE_-fnco-zvOWV8KWl0B1id1IaVJqe9Svot_39EedQeMZl299bActIFI0r8eEiLl740WxKnLx" +
            "PQixSYYYHBqzMqs8ZrbsGvlcHPbmqG8fBlqMtZX-i67cRqzzPOvJ7MsuIpd1KlUycPxyABwUVC7YU0kS7Ey0_slP_OjzQM5BOXOnX0so" +
            "EF1zb----7KfVYW1gktQbDa14q9_XFj5gFP94FgYb06ACiTjJ_i3RDcA78Mg8FccsBK449B0y-9WTRix0";

    static String getURL(String url) {
        return ResourceBundle.getBundle("routes").getString(url);
    }
}
