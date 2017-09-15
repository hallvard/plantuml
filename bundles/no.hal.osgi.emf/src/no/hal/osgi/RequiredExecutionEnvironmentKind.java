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
	JAVA8(8, "Java8", "JavaSE-1.8");

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
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
