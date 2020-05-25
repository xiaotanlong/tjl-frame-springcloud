package com.tjl.base.dao.mybatisplus;

import java.io.Serializable;

/**
 * 功能描述
 * @author tjl
 * @Type BaseModel
 * @date 2020/5/25 14:47
 * @Version 1.0
 */
public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = 141225L;

    protected BaseModel(BaseModel.BaseModelBuilder<?, ?> b) {
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseModel)) {
            return false;
        } else {
            BaseModel other = (BaseModel)o;
            return other.canEqual(this);
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseModel;
    }

    @Override
    public int hashCode() {
        int result = 1;
        return result;
    }

    @Override
    public String toString() {
        return "BaseModel()";
    }

    public BaseModel() {
    }

    public abstract static class BaseModelBuilder<C extends BaseModel, B extends BaseModel.BaseModelBuilder<C, B>> {
        public BaseModelBuilder() {
        }

        protected abstract B self();

        public abstract C build();

        @Override
        public String toString() {
            return "BaseModel.BaseModelBuilder()";
        }
    }
}
