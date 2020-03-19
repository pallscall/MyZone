package com.example.demo.Exception;

public enum ErrorCode implements IErrorCode{

    QUESTION_NOT_FOUND(2001,"Blog does not exsit!!!"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复！！"),
    NO_LOGIN(2003,"当前操作需先登录"),
    SYS_ERROR(2004,"Server was died!"),
    TYPE_PARAM_WRONG(2005,"评论类型不存在"),
    COMMENT_NOT_FOUND(2006,"评论不存在"),
    CONTENT_IS_EMPTY(2007,"内容不能为空")
    ;

    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }

    private int code;
    private String message;
    ErrorCode(int code,String message){
        this.code = code;
        this.message = message;
    }
}
