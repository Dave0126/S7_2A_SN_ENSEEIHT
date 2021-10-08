/**
 */
package simplepdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ressource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link simplepdl.Ressource#getName <em>Name</em>}</li>
 *   <li>{@link simplepdl.Ressource#getNbOccurance <em>Nb Occurance</em>}</li>
 * </ul>
 *
 * @see simplepdl.SimplepdlPackage#getRessource()
 * @model
 * @generated
 */
public interface Ressource extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see simplepdl.SimplepdlPackage#getRessource_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link simplepdl.Ressource#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Nb Occurance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nb Occurance</em>' attribute.
	 * @see #setNbOccurance(int)
	 * @see simplepdl.SimplepdlPackage#getRessource_NbOccurance()
	 * @model
	 * @generated
	 */
	int getNbOccurance();

	/**
	 * Sets the value of the '{@link simplepdl.Ressource#getNbOccurance <em>Nb Occurance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nb Occurance</em>' attribute.
	 * @see #getNbOccurance()
	 * @generated
	 */
	void setNbOccurance(int value);

} // Ressource
