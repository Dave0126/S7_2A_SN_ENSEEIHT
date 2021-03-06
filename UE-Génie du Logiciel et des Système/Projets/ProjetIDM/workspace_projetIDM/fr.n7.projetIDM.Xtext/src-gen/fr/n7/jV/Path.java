/**
 * generated by Xtext 2.23.0
 */
package fr.n7.jV;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Path</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.n7.jV.Path#getDescription <em>Description</em>}</li>
 *   <li>{@link fr.n7.jV.Path#getOpen <em>Open</em>}</li>
 *   <li>{@link fr.n7.jV.Path#getVisible <em>Visible</em>}</li>
 *   <li>{@link fr.n7.jV.Path#getMandatory <em>Mandatory</em>}</li>
 *   <li>{@link fr.n7.jV.Path#getConsumedID <em>Consumed ID</em>}</li>
 * </ul>
 *
 * @see fr.n7.jV.JVPackage#getPath()
 * @model
 * @generated
 */
public interface Path extends GameElement
{
  /**
   * Returns the value of the '<em><b>Description</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Description</em>' containment reference.
   * @see #setDescription(Description)
   * @see fr.n7.jV.JVPackage#getPath_Description()
   * @model containment="true"
   * @generated
   */
  Description getDescription();

  /**
   * Sets the value of the '{@link fr.n7.jV.Path#getDescription <em>Description</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Description</em>' containment reference.
   * @see #getDescription()
   * @generated
   */
  void setDescription(Description value);

  /**
   * Returns the value of the '<em><b>Open</b></em>' containment reference list.
   * The list contents are of type {@link fr.n7.jV.Condition}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Open</em>' containment reference list.
   * @see fr.n7.jV.JVPackage#getPath_Open()
   * @model containment="true"
   * @generated
   */
  EList<Condition> getOpen();

  /**
   * Returns the value of the '<em><b>Visible</b></em>' containment reference list.
   * The list contents are of type {@link fr.n7.jV.Condition}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Visible</em>' containment reference list.
   * @see fr.n7.jV.JVPackage#getPath_Visible()
   * @model containment="true"
   * @generated
   */
  EList<Condition> getVisible();

  /**
   * Returns the value of the '<em><b>Mandatory</b></em>' attribute.
   * The literals are from the enumeration {@link fr.n7.jV.BOOLEAN}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mandatory</em>' attribute.
   * @see fr.n7.jV.BOOLEAN
   * @see #setMandatory(BOOLEAN)
   * @see fr.n7.jV.JVPackage#getPath_Mandatory()
   * @model
   * @generated
   */
  BOOLEAN getMandatory();

  /**
   * Sets the value of the '{@link fr.n7.jV.Path#getMandatory <em>Mandatory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mandatory</em>' attribute.
   * @see fr.n7.jV.BOOLEAN
   * @see #getMandatory()
   * @generated
   */
  void setMandatory(BOOLEAN value);

  /**
   * Returns the value of the '<em><b>Consumed ID</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Consumed ID</em>' attribute list.
   * @see fr.n7.jV.JVPackage#getPath_ConsumedID()
   * @model unique="false"
   * @generated
   */
  EList<String> getConsumedID();

} // Path
