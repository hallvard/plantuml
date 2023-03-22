/**
 */
package no.hal.osgi;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Required Execution Environment Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see no.hal.osgi.OsgiPackage#getRequiredExecutionEnvironmentKind()
 * @model
 * @generated
 */
public enum RequiredExecutionEnvironmentKind implements Enumerator {
	/**
	 * The '<em><b>Default</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEFAULT_VALUE
	 * @generated
	 * @ordered
	 */
	DEFAULT(0, "default", "default"),

	/**
	 * The '<em><b>Java4</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA4_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA4(4, "Java4", "J2SE-1.4"),

	/**
	 * The '<em><b>Java5</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA5_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA5(5, "Java5", "J2SE-1.5"),

	/**
	 * The '<em><b>Java6</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA6_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA6(6, "Java6", "JavaSE-1.6"),

	/**
	 * The '<em><b>Java7</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA7_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA7(7, "Java7", "JavaSE-1.7"),

	/**
	 * The '<em><b>Java8</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA8_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA8(8, "Java8", "JavaSE-1.8"), /**
	 * The '<em><b>Java9</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA9_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA9(9, "Java9", "JavaSE-9"), /**
	 * The '<em><b>Java10</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA10_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA10(10, "Java10", "JavaSE-10"), /**
	 * The '<em><b>Java11</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA11_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA11(11, "Java11", "JavaSE-11"), /**
	 * The '<em><b>Java12</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA12_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA12(12, "Java12", "JavaSE-12"), /**
	 * The '<em><b>Java13</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA13_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA13(13, "Java13", "JavaSE-13"), /**
	 * The '<em><b>Java14</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA14_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA14(14, "Java14", "JavaSE-14"), /**
	 * The '<em><b>Java15</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA15_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA15(15, "Java15", "JavaSE-15"), /**
	 * The '<em><b>Java16</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA16_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA16(16, "Java16", "JavaSE-16"), /**
	 * The '<em><b>Java17</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA17_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA17(17, "Java17", "JavaSE-17"), /**
	 * The '<em><b>Java18</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA18_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA18(18, "Java18", "JavaSE-18"), /**
	 * The '<em><b>Java19</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA19_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA19(19, "Java19", "JavaSE-19");

	/**
	 * The '<em><b>Default</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Default</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DEFAULT
	 * @model name="default"
	 * @generated
	 * @ordered
	 */
	public static final int DEFAULT_VALUE = 0;

	/**
	 * The '<em><b>Java4</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java4</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA4
	 * @model name="Java4" literal="J2SE-1.4"
	 * @generated
	 * @ordered
	 */
	public static final int JAVA4_VALUE = 4;

	/**
	 * The '<em><b>Java5</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java5</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA5
	 * @model name="Java5" literal="J2SE-1.5"
	 * @generated
	 * @ordered
	 */
	public static final int JAVA5_VALUE = 5;

	/**
	 * The '<em><b>Java6</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java6</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA6
	 * @model name="Java6" literal="JavaSE-1.6"
	 * @generated
	 * @ordered
	 */
	public static final int JAVA6_VALUE = 6;

	/**
	 * The '<em><b>Java7</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java7</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA7
	 * @model name="Java7" literal="JavaSE-1.7"
	 * @generated
	 * @ordered
	 */
	public static final int JAVA7_VALUE = 7;

	/**
	 * The '<em><b>Java8</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Java8</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVA8
	 * @model name="Java8" literal="JavaSE-1.8"
	 * @generated
	 * @ordered
	 */
	public static final int JAVA8_VALUE = 8;

	/**
	 * The '<em><b>Java9</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA9
	 * @model name="Java9" literal="JavaSE-9"
	 * @generated
	 * @ordered
	 */
	public static final int JAVA9_VALUE = 9;

	/**
	 * The '<em><b>Java10</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA10
	 * @model name="Java10" literal="JavaSE-10"
	 * @generated
	 * @ordered
	 */
	public static final int JAVA10_VALUE = 10;

	/**
	 * The '<em><b>Java11</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA11
	 * @model name="Java11" literal="JavaSE-11"
	 * @generated
	 * @ordered
	 */
	public static final int JAVA11_VALUE = 11;

	/**
	 * The '<em><b>Java12</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA12
	 * @model name="Java12" literal="JavaSE-12"
	 * @generated
	 * @ordered
	 */
	public static final int JAVA12_VALUE = 12;

	/**
	 * The '<em><b>Java13</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA13
	 * @model name="Java13" literal="JavaSE-13"
	 * @generated
	 * @ordered
	 */
	public static final int JAVA13_VALUE = 13;

	/**
	 * The '<em><b>Java14</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA14
	 * @model name="Java14" literal="JavaSE-14"
	 * @generated
	 * @ordered
	 */
	public static final int JAVA14_VALUE = 14;

	/**
	 * The '<em><b>Java15</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA15
	 * @model name="Java15" literal="JavaSE-15"
	 * @generated
	 * @ordered
	 */
	public static final int JAVA15_VALUE = 15;

	/**
	 * The '<em><b>Java16</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA16
	 * @model name="Java16" literal="JavaSE-16"
	 * @generated
	 * @ordered
	 */
	public static final int JAVA16_VALUE = 16;

	/**
	 * The '<em><b>Java17</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA17
	 * @model name="Java17" literal="JavaSE-17"
	 * @generated
	 * @ordered
	 */
	public static final int JAVA17_VALUE = 17;

	/**
	 * The '<em><b>Java18</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA18
	 * @model name="Java18" literal="JavaSE-18"
	 * @generated
	 * @ordered
	 */
	public static final int JAVA18_VALUE = 18;

	/**
	 * The '<em><b>Java19</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA19
	 * @model name="Java19" literal="JavaSE-19"
	 * @generated
	 * @ordered
	 */
	public static final int JAVA19_VALUE = 19;

	/**
	 * An array of all the '<em><b>Required Execution Environment Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final RequiredExecutionEnvironmentKind[] VALUES_ARRAY =
		new RequiredExecutionEnvironmentKind[] {
			DEFAULT,
			JAVA4,
			JAVA5,
			JAVA6,
			JAVA7,
			JAVA8,
			JAVA9,
			JAVA10,
			JAVA11,
			JAVA12,
			JAVA13,
			JAVA14,
			JAVA15,
			JAVA16,
			JAVA17,
			JAVA18,
			JAVA19,
		};

	/**
	 * A public read-only list of all the '<em><b>Required Execution Environment Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<RequiredExecutionEnvironmentKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Required Execution Environment Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static RequiredExecutionEnvironmentKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RequiredExecutionEnvironmentKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Required Execution Environment Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static RequiredExecutionEnvironmentKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RequiredExecutionEnvironmentKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Required Execution Environment Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static RequiredExecutionEnvironmentKind get(int value) {
		switch (value) {
			case DEFAULT_VALUE: return DEFAULT;
			case JAVA4_VALUE: return JAVA4;
			case JAVA5_VALUE: return JAVA5;
			case JAVA6_VALUE: return JAVA6;
			case JAVA7_VALUE: return JAVA7;
			case JAVA8_VALUE: return JAVA8;
			case JAVA9_VALUE: return JAVA9;
			case JAVA10_VALUE: return JAVA10;
			case JAVA11_VALUE: return JAVA11;
			case JAVA12_VALUE: return JAVA12;
			case JAVA13_VALUE: return JAVA13;
			case JAVA14_VALUE: return JAVA14;
			case JAVA15_VALUE: return JAVA15;
			case JAVA16_VALUE: return JAVA16;
			case JAVA17_VALUE: return JAVA17;
			case JAVA18_VALUE: return JAVA18;
			case JAVA19_VALUE: return JAVA19;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private RequiredExecutionEnvironmentKind(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //RequiredExecutionEnvironmentKind
