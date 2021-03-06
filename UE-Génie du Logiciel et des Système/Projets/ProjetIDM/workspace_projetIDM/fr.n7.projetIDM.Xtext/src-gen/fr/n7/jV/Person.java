/**
 * generated by Xtext 2.23.0
 */
package fr.n7.jV;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Person</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.n7.jV.Person#getId <em>Id</em>}</li>
 *   <li>{@link fr.n7.jV.Person#getVisible <em>Visible</em>}</li>
 *   <li>{@link fr.n7.jV.Person#getMandatory <em>Mandatory</em>}</li>
 *   <li>{@link fr.n7.jV.Person#getActions <em>Actions</em>}</li>
 * </ul>
 *
 * @see fr.n7.jV.JVPackage#getPerson()
 * @model
 * @generated
 */
public interface Person extends EObject
{
  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see fr.n7.jV.JVPackage#getPerson_Id()
   * @model
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link fr.n7.jV.Person#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>Visible</b></em>' attribute.
   * The literals are from the enumeration {@link fr.n7.jV.BOOLEAN}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Visible</em>' attribute.
   * @see fr.n7.jV.BOOLEAN
   * @see #setVisible(BOOLEAN)
   * @see fr.n7.jV.JVPackage#getPerson_Visible()
   * @model
   * @generated
   */
  BOOLEAN getVisible();

  /**
   * Sets the value of the '{@link fr.n7.jV.Person#getVisible <em>Visible</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Visible</em>' attribute.
   * @see fr.n7.jV.BOOLEAN
   * @see #getVisible()
   * @generated
   */
  void setVisible(BOOLEAN value);

  /**
   * Returns the value of the '<em><b>Mandatory</b></em>' attribute.
   * The literals are from the enumeration {@link fr.n7.jV.BOOLEAN}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mandatory</em>' attribute.
   * @see fr.n7.jV.BOOLEAN
   * @see #setMandatory(BOOLEAN)
   * @see fr.n7.jV.JVPackage#getPerson_Mandatory()
   * @model
   * @generated
   */
  BOOLEAN getMandatory();

  /**
   * Sets the value of the '{@link fr.n7.jV.Person#getMandatory <em>Mandatory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mandatory</em>' attribute.
   * @see fr.n7.jV.BOOLEAN
   * @see #getMandatory()
   * @generated
   */
  void setMandatory(BOOLEAN value);

  /**
   * Returns the value of the '<em><b>Actions</b></em>' containment reference list.
   * The list contents are of type {@link fr.n7.jV.Action}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Actions</em>' containment reference list.
   * @see fr.n7.jV.JVPackage#getPerson_Actions()
   * @model containment="true"
   * @generated
   */
  EList<Action> getActions();

} // Person
