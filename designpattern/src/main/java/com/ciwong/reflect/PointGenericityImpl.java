package com.ciwong.reflect;

import java.io.Serializable;

public class PointGenericityImpl<T extends Number&Serializable> implements IPoint<T,Integer> {
}
