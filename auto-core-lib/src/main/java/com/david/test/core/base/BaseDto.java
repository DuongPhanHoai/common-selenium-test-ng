package com.david.test.core.base;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class BaseDto {
    static Gson gson = new Gson();

    @Override
    public String toString() {
        // Convert to JSON Obj
        return gson.toJson(this);
    }
}
