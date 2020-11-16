package com.hulu.shop.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.ebeaninternal.server.lib.Str;
import org.springframework.validation.FieldError;

public class ResponseObject {
    private String SCHEMATYPE_NULL = "null";
    private String SCHEMATYPE_ARRAY = "array";
    private String SCHEMATYPE_OBJECT = "object";


    private Integer code = ResponseCode.OK;
    private String msg = "";
    private String schemaType = SCHEMATYPE_NULL;

    private String schemaFields = "";
    private String schemaPages = "";
    private JsonElement data;

    private JsonArray genSchema() {
        JsonArray schema = new JsonArray();
        schema.add(schemaType);
        schema.add(schemaFields);
        schema.add(schemaPages);
        return schema;
    }


    public ResponseObject(Integer code) {
        this.code = code;
    }

    public ResponseObject(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResponseObject setArrayData(Object data) {
        Gson gson = new Gson();
        if (data == null) {
            this.data = new JsonArray();
        } else {
            this.data = gson.toJsonTree(data);
        }
        this.schemaType = SCHEMATYPE_ARRAY;
        return this;
    }

    public JsonObject getObjectData() {
        if (this.data == null) {
            return null;
        }
        if (this.data.isJsonObject()) {
            return this.data.getAsJsonObject();
        }
        return null;
    }

    public ResponseObject setObjectData(Object data) {
        Gson gson = new Gson();
        this.data = gson.toJsonTree(data);
        this.schemaType = SCHEMATYPE_OBJECT;
        return this;
    }

    public ResponseObject setJsonData(JsonObject data) {
        this.data = data;
        this.schemaType = SCHEMATYPE_OBJECT;
        return this;
    }

    public JsonElement getData() {
        return this.data;
    }

    public ResponseObject setData(Object data) {
        if (data != null) {
            Gson gson = new Gson();
            this.data = gson.toJsonTree(data);
            this.schemaType = SCHEMATYPE_OBJECT;
        }
        return this;
    }

    public String converterToString() {
        JsonObject j = new JsonObject();
        j.addProperty("code", code);
        j.addProperty("msg", msg);
        j.add("schema", genSchema());
        if (data != null) {
            j.add("data", data);
        }
        return j.toString();
    }


    @Override
    public String toString() {
        return "";
    }


    public final ResponseObject error(Integer code, String msg) {
        ResponseObject r = new ResponseObject(code, msg);
        return r;
    }

    public final ResponseObject error(org.springframework.validation.Errors errors) {
        Gson gson = new Gson();
        if (!errors.hasErrors()) {
            return new ResponseObject(ResponseCode.INTERNAL_ERROR, "服务器内部错误");
        }
        if (errors.hasFieldErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                String msg = String.format("%s", error.getCode());
//                System.out.println(gson.toJsonTree(error.getCodes()));
//                    if (error.getCodes()) {
//                        String[] tmp = error.getCodes();
//                        msg = tmp[1];
//                    }
                return new ResponseObject(ResponseCode.INVALID_PARAMETER, msg);
            }
        }
        return new ResponseObject(ResponseCode.INTERNAL_ERROR, "服务器错误");
    }

    public final ResponseObject error(org.springframework.validation.BindingResult errors) {
        if (!errors.hasErrors()) {
            return new ResponseObject(ResponseCode.INTERNAL_ERROR, "服务器内部错误");
        }
        if (errors.hasFieldErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                String msg = String.format("%s:%s", error.getField(), error.getCode());
            }
        }
        return new ResponseObject(ResponseCode.INTERNAL_ERROR, "服务器错误");
    }

}
