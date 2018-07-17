package com.ciwong.reflect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ciwong.designpattern.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;


public class Main2Activity extends AppCompatActivity {
	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		Button button = (Button) findViewById(R.id.btn);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					demoFunc();
				} catch (Exception e) {
					System.out.print(e.getMessage());
				}
			}

		});

		//		demo1();
		//		demo2();
		//		demo3();
		//		demo4();
		/*泛型类*/
		//		dmeo5();
		//		demo6();
		//		dmeo7();
		//		demo8();
		//		demo9();
		//		parseClass(PointArrayImpl2.class);
		//         Constructor
		//		demo10();
		//		demo11();
		//		dmeo12();
		//		demo13();
		//		demo14();
		//获取Field对象
		//		demo15();
		//		dmeo16();
		//		demo17();
		//		demo18();
		demo19();
	}

	private void demo19() {
		Class person = Person.class;
		Method method = null;
		try {
			method = person.getDeclaredMethod("setName",String.class);
			Log.e(TAG,"得到指定方法："+method.toString());

			method = person.getDeclaredMethod("testInvoke", Integer.class,String.class);

			method.setAccessible(true);
			Boolean result = (Boolean)method.invoke(person, 25, "I m harvic");
			Log.d(TAG,"执行结果:"+result);

			Class<?>[] params = method.getParameterTypes();
			for (Class c:params){
				Log.e(TAG,"枚举到参数类型："+c.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void demo18() {
		Class<?> clazz2 = Person.COLOR.class;
		Field field = null;
		try {
			field = clazz2.getDeclaredField("WHITE");
			Log.e(TAG,"COLOR.WHITE是否是枚举常量："+field.isEnumConstant()+"");

			Class<?> clazz = Person.class;
			Field fColor = clazz.getDeclaredField("color");
			fColor.setAccessible(true);
			boolean isEnum = fColor.isEnumConstant();
			Log.e(TAG,"color是否是枚举常量："+isEnum);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

	}

	private void demo17() {
		Class<?> clazz = Person.class;
		Constructor<?> constructor = null;
		try {
			constructor = clazz.getConstructor();
			Person person = (Person)constructor.newInstance();

			Field fName = clazz.getDeclaredField("name");
			fName.setAccessible(true);
			fName.set(person, "qijian");
			String val = (String)fName.get(person);

			Field fName2= clazz.getDeclaredField("age");
			fName2.setAccessible(true);
			fName2.set(person, 12);
			Log.e(TAG, "fieldName:" + val + "   personName:" + person.getName()+ "   personAge:" + person.getAge());
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	private void dmeo16() {
		Class<?> clazz = Person.class;
		Field field = null;
		try {
			field = clazz.getDeclaredField("age");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		field.setAccessible(true);
		Class<?> type = field.getType();
		Log.e(TAG,"得到age对应的field:"+type.getName()+"  "+field.getName());
	}

	private void demo15() {
		Class<?> clazz = Person.class;
		Field[] fields = clazz.getDeclaredFields();
		for (Field field:fields){
			field.setAccessible(true);
			Class<?> type = field.getType();
			Log.e(TAG,"枚举到的field:"+type.getName()+"  "+field.getName());
		}
	}

	private void demo14() {
		Class<?> clazz = Person.class;
		Constructor<?> constructor = null;
		try {
			constructor = clazz.getDeclaredConstructor();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		Class<?> declarClazz = constructor.getDeclaringClass();
		Log.e(TAG,declarClazz.getName());
	}

	private void demo13() {
		Class<?> clazz = Person.class;
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();
		for (Constructor<?> c:constructors) {
			c.setAccessible(true);

			int modifier = c.getModifiers();
			Log.e(TAG,"一个访问修饰符为："+Modifier.toString(modifier));
		}
	}

	private void dmeo12() {
		Class<?> clazz = Person.class;
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();
		for (Constructor<?> c:constructors){
			c.setAccessible(true);
			Class<?>[] types = c.getParameterTypes();

			StringBuilder builder = new StringBuilder("获取参数类型为：");
			for(Class t:types){
				builder.append(t.getName());
				builder.append("   ");
			}
			Log.e(TAG,builder.toString());
		}
	}

	private void demo11() {
		try {
			Class<?> clazz = Person.class;
			Constructor<?> constructor = clazz.getDeclaredConstructor(Integer.class, String.class);
			constructor.setAccessible(true);

			//构造实例一
			Person person1 = (Person) constructor.newInstance(new Integer(30),new String("harvic"));
			Log.e(TAG, "构造的参数为：" + person1.getName() + "  " + person1.getAge());

			//构造实例二
			Person person2 = (Person) constructor.newInstance(50,"qijian");
			Log.e(TAG,"构造的参数为："+person2.getName() + "  "+ person2.getAge());

			//构造实例三
			Person person3 = (Person) constructor.newInstance();
			person3.setAge(30);
			person3.setName("qijian");
			Log.e(TAG,"构造的参数为："+person3.getName() + "  "+ person3.getAge());
		} catch (Exception e) {

		}

	}

	private void demo10() {
		//1、枚举
		Class<?> clazz = Person.class;
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();
		for (Constructor item:constructors){
			Log.e(TAG,"枚举到的构造函数："+item.toString());
		}

		//2、根据类型，获取指定的构造的构造函数
		Constructor<?> constructor = null;
		try {
			constructor = clazz.getDeclaredConstructor(Integer.class, String.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		Log.e(TAG, "指定参数得到的构造函数："+constructor.toString());
	}

	private void parseClass(Class<?> c){
		parseTypeParameters(c.getGenericInterfaces());
	}
	private void parseTypeParameters(Type[] types){
		for(Type type:types){
			parseTypeParameter(type);
		}
	}

	private void parseTypeParameter(Type type){
		if(type instanceof Class){
			Class<?> c = (Class<?>) type;
			Log.e(TAG, c.getSimpleName());
		} else if(type instanceof TypeVariable){
			TypeVariable<?> tv = (TypeVariable<?>)type;
			Log.e(TAG, tv.getName());
			parseTypeParameters(tv.getBounds());
		} else if(type instanceof WildcardType){
			WildcardType wt = (WildcardType)type;
			Log.e(TAG, "?");
			parseTypeParameters(wt.getUpperBounds());
			parseTypeParameters(wt.getLowerBounds());
		} else if(type instanceof ParameterizedType){
			ParameterizedType pt = (ParameterizedType)type;
			Type t = pt.getOwnerType();
			if(t != null) {
				parseTypeParameter(t);
			}
			parseTypeParameter(pt.getRawType());
			parseTypeParameters(pt.getActualTypeArguments());
		} else if (type instanceof GenericArrayType){
			GenericArrayType arrayType = (GenericArrayType)type;
			Type t = arrayType.getGenericComponentType();
			parseTypeParameter(t);
		}
	}
	private void demo9() {
		Class<?> clazz = PointArrayImpl2.class;
		//此时的type对应PointSingleInterface<Comparable<? extends Number>>
		Type[] types = clazz.getGenericInterfaces();
		for (Type type:types) {
			if (type instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				//得到填充PointSingleInterface的具体参数，即：Comparable<? extends Number>，仍然是一个ParameterizedType
				Type[] actualTypes = parameterizedType.getActualTypeArguments();
				for (Type actualType : actualTypes) {
					if (actualType instanceof ParameterizedType) {
						ParameterizedType ComparableType = (ParameterizedType) actualType;
						//对Comparable<? extends Number>再取填充参数，得到的type对应<? extends Number>，这个就是WildcardType了
						Type[] compareArgs = ComparableType.getActualTypeArguments();
						for (Type Arg:compareArgs){
							if(Arg instanceof WildcardType){
								//将得到的对应WildcardType的type强转为WildcardType的变量
								WildcardType wt = (WildcardType) Arg;

								//利用getLowerBounds得到下界，即派生自Super的限定，如果没有派生自super则为null
								Type[] lowerBounds = wt.getLowerBounds();
								for (Type bound:lowerBounds){
									Class<?> boundClass = (Class)bound;
									Log.e(TAG, "lowerBound为：" + boundClass.getName());
								}

								//通过getUpperBounds得到上界，即派生自extends的限定，如果没有，默认是Object
								Type[] upperBounds = wt.getUpperBounds();
								for (Type bound:upperBounds){
									Class<?> boundClass = (Class)bound;
									//如果不写，则默认输出Object，如果写了，则输出对应的
									Log.e(TAG, "upperBound为：" + boundClass.getName());
								}

							}
						}
					}
				}

			}
		}
	}

	private void demo8() {
		Class<?> clazz = PointArrayImpl.class;
		Type[] interfaces = clazz.getGenericInterfaces();
		for (Type type:interfaces){
			if (type instanceof ParameterizedType) {
				ParameterizedType pt = (ParameterizedType) type;
				Type[] actualArgs = pt.getActualTypeArguments();
				for (Type arg:actualArgs){
					if (arg instanceof GenericArrayType){
						GenericArrayType arrayType = (GenericArrayType)arg;
						Type comType = arrayType.getGenericComponentType();
						Class<?> typeClass = (Class)comType;
						Log.e(TAG,"数组类型为："+typeClass.getName());
					}
				}
			}
		}
	}

	private void dmeo7() {
		PointGenericityImpl numberPointGenericity = new PointGenericityImpl<>();
		Class<?> clazz = numberPointGenericity.getClass();
		Type[] types = clazz.getGenericInterfaces();
		for (Type type:types) {
			if (type instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				//返回表示此类型实际类型参数的 Type 对象的数组
				Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
				for (Type parameterArgType : actualTypeArguments) {

					if(parameterArgType instanceof TypeVariable){
						TypeVariable typeVariable = (TypeVariable) parameterArgType;
						Log.e(TAG, "此接口的填充类型为：" + typeVariable.getName());

						//返回表示此类型变量上边界的 Type 对象的数组。
						Type[] typebounds = typeVariable.getBounds();
						for (Type bound:typebounds){
							Class<?> boundClass = (Class)bound;
							//如果不写，则默认输出Object，如果写了，则输出对应的
							Log.e(TAG, "bound为：" + boundClass.getName());
						}
					}
					if (parameterArgType instanceof  Class){
						Class parameterArgClass = (Class) parameterArgType;
						Log.d(TAG, "此接口的填充类型为：" + parameterArgClass.getName());
					}
				}

			}
		}
	}

	private void demo6() {
		Class<?> clazz = PointImpl.class;
		Type[] types = clazz.getGenericInterfaces();
		for (Type type:types) {
			if (type instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				//返回表示此类型实际类型参数的 Type 对象的数组
				Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
				for (Type parameterArgType : actualTypeArguments) {
					Class parameterArgClass = (Class) parameterArgType;
					Log.e(TAG, "此接口的填充类型为：" + parameterArgClass.getName());
				}

				//返回 Type 对象，表示声明此类型的类或接口。
				Type type1 = parameterizedType.getRawType();
				Class class22 = (Class) type1;
				Log.e(TAG,"声明此接口的类型为："+class22.getName());
			}
		}
	}

	private void dmeo5() {
		Class<?> clazz = PointImpl.class;
		Type type = clazz.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			//返回表示此类型实际类型参数的 Type 对象的数组
			Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
			for (Type parameterArgType : actualTypeArguments) {
				Class parameterArgClass = (Class) parameterArgType;
				Log.e(TAG,"填充类型为：" + parameterArgClass.getName());
			}

			//返回 Type 对象，表示声明此类型的类或接口。
			Type type1 = parameterizedType.getRawType();
			Class class22 = (Class) type1;
			Log.e(TAG,"PointImpl的父类类型为："+class22.getName());

		}
	}

	private void demo4() {
		try {
			Class<?> clazz = getClassLoader().loadClass(InnerClass.class.getName());
			int modifiers = clazz.getModifiers();
			String retval = Modifier.toString(modifiers);
			boolean isFinal = Modifier.isFinal(modifiers);
			Log.e(TAG, "InnerClass的定义修饰符:" + retval);
			Log.e(TAG, "is Final:" + isFinal);
		}catch (Exception e){

		}

	}

	private void demo3() {
		//获取Animal类的接口列表
		Class<?> class3 = Animal.class;
		Class<?>[] interfaces = class3.getInterfaces();
		for (Class interItem:interfaces){
			Log.d(TAG, "Animal继承的接口：" + interItem.getName());
		}

		//获取AnimalImpl的接口列表
		class3 = AnimalImpl.class;
		interfaces = class3.getInterfaces();
		if (interfaces.length >0) {
			for (Class interItem : interfaces) {
				Log.d(TAG, "AnimalImpl继承的接口：" + interItem.getName());
			}
		}else {
			Log.d(TAG, "AnimalImpl无继承的接口");
		}
	}

	private void demo2() {
		try {
			Class<?> class2 = Class.forName("com.yhao.floatwindow.reflect.AnimalImpl");
			Class<?> parentClass = class2.getSuperclass();
			Log.d(TAG, "父类：" + parentClass.getName());
		}catch (Exception e){

		}

	}

	private void demo1() {
		Class<?> class1 = Animal.class;
		Package package1 = class1.getPackage();

		Log.e(TAG,"完整的类名："+class1.getName());
		Log.e(TAG,"仅获取类名："+class1.getSimpleName());
		Log.e(TAG,"包名："+getPackageName());
	}

	public void demoFunc()throws Exception{
		Class<?> class1 = Class.forName("com.yhao.floatwindow.reflect.Animal");
		Log.e(TAG,"通过Class.forName获得的类名："+class1.getName());

		class1 = getClassLoader().loadClass("com.yhao.floatwindow.reflect.Animal");
		Log.e(TAG,"通过ClassLoader获得的类名："+class1.getName());
	}


	public static final class InnerClass{
	}
}