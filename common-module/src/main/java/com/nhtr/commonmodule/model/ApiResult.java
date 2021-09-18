package com.nhtr.commonmodule.model;

import com.nhtr.commonmodule.constant.WebConst;

public interface ApiResult {
    ApiResult OK = new ApiResult() {

        @Override
        public boolean isOk() {
            return true;
        }

        @Override
        public String getCode() {
            return WebConst.SUCCESS;
        }

        @Override
        public String getMessage() {
            return "OK";
        }
    };

    ApiResult FAIL = new ApiResult() {

        @Override
        public boolean isOk() {
            return false;
        }

        @Override
        public String getCode() {
            return WebConst.FAIL;
        }

        @Override
        public String getMessage() {
            return "Transaction fail";
        }
    };

    ApiResult BAD = new ApiResult() {

        @Override
        public boolean isOk() {
            return false;
        }

        @Override
        public String getCode() {
            return WebConst.BAD_REQUEST;
        }

        @Override
        public String getMessage() {
            return "Request invalid";
        }
    };

    ApiResult NOT_FOUND = new ApiResult() {

        @Override
        public boolean isOk() {
            return false;
        }

        @Override
        public String getCode() {
            return WebConst.NOT_FOUND;
        }

        @Override
        public String getMessage() {
            return "This method isn't supported";
        }
    };

    ApiResult INVALID = new ApiResult() {

        @Override
        public boolean isOk() {
            return false;
        }

        @Override
        public String getCode() {
            return WebConst.ARGS_INVALID;
        }

        @Override
        public String getMessage() {
            return "Request argument not valid";
        }
    };

    ApiResult EXCEPTION = new ApiResult() {

        @Override
        public boolean isOk() {
            return false;
        }

        @Override
        public String getCode() {
            return WebConst.EXCEPTION;
        }

        @Override
        public String getMessage() {
            return "An error occurred, Please try again later or contact to admin for detail";
        }
    };

    ApiResult NOT_EXISTS = new ApiResult() {

        @Override
        public boolean isOk() {
            return false;
        }

        @Override
        public String getCode() {
            return WebConst.ITEM_NOT_EXIST;
        }

        @Override
        public String getMessage() {
            return "Item not exists in our data. Please try again.";
        }
    };

    ApiResult EXISTED = new ApiResult() {

        @Override
        public boolean isOk() {
            return false;
        }

        @Override
        public String getCode() {
            return WebConst.ITEM_EXISTED;
        }

        @Override
        public String getMessage() {
            return "Item was existed in our data. Please try again.";
        }
    };

    ApiResult APPROVED = new ApiResult() {

        @Override
        public boolean isOk() {
            return false;
        }

        @Override
        public String getCode() {
            return WebConst.ITEM_APPROVED;
        }

        @Override
        public String getMessage() {
            return "Item was approved. Please try again with other data.";
        }
    };

    ApiResult NOT_PERMISSION = new ApiResult() {

        @Override
        public boolean isOk() {
            return false;
        }

        @Override
        public String getCode() {
            return WebConst.NOT_PERMISSION;
        }

        @Override
        public String getMessage() {
            return "You cannot access the data. Please contact with administrator for more detail.";
        }
    };

    ApiResult OVER_TRAFFIC = new ApiResult() {

        @Override
        public boolean isOk() {
            return false;
        }

        @Override
        public String getCode() {
            return WebConst.UNUSUAL_TRAFFIC;
        }

        @Override
        public String getMessage() {
            return "Your traffic limited. Please try again later or contact to administrator for more detail.";
        }
    };

    boolean isOk();

    String getCode();

    String getMessage();
}
