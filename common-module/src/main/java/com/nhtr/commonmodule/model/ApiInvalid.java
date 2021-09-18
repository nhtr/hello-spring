package com.nhtr.commonmodule.model;

import com.nhtr.commonmodule.constant.WebConst;

public interface ApiInvalid {
    ApiInvalid NULL = new ApiInvalid() {

        @Override
        public String getCode() {
            return WebConst.INVALID_NULL;
        }

        @Override
        public String getMessage() {
            return "Value ApiInvalid null";
        }
    };

    ApiInvalid EMPTY = new ApiInvalid() {

        @Override
        public String getCode() {
            return WebConst.INVALID_EMPTY;
        }

        @Override
        public String getMessage() {
            return "Value ApiInvalid empty";
        }
    };

    ApiInvalid MAX = new ApiInvalid() {

        @Override
        public String getCode() {
            return WebConst.INVALID_MAX;
        }

        @Override
        public String getMessage() {
            return "Value ApiInvalid max";
        }
    };

    ApiInvalid MIN = new ApiInvalid() {

        @Override
        public String getCode() {
            return WebConst.INVALID_MIN;
        }

        @Override
        public String getMessage() {
            return "Value ApiInvalid min";
        }
    };

    ApiInvalid FORMAT = new ApiInvalid() {

        @Override
        public String getCode() {
            return WebConst.INVALID_FORMAT;
        }

        @Override
        public String getMessage() {
            return "Value ApiInvalid fomat";
        }
    };

    ApiInvalid RANGE = new ApiInvalid() {

        @Override
        public String getCode() {
            return WebConst.INVALID_RANGE;
        }

        @Override
        public String getMessage() {
            return "Value ApiInvalid range";
        }
    };

    ApiInvalid MIN_LENGTH = new ApiInvalid() {

        @Override
        public String getCode() {
            return WebConst.INVALID_MINLENGTH;
        }

        @Override
        public String getMessage() {
            return "Value ApiInvalid min length";
        }
    };

    ApiInvalid MAX_LENGTH = new ApiInvalid() {

        @Override
        public String getCode() {
            return WebConst.INVALID_MAXLENGTH;
        }

        @Override
        public String getMessage() {
            return "Value ApiInvalid max length";
        }
    };

    ApiInvalid UNKNOWN = new ApiInvalid() {

        @Override
        public String getCode() {
            return WebConst.INVALID_UNKNOWN;
        }

        @Override
        public String getMessage() {
            return "Unknown ApiInvalid code";
        }
    };

    String getCode();

    String getMessage();
}
