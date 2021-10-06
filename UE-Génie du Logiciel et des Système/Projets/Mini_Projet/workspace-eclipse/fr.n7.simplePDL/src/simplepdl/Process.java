/**
 */
package simplepdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link simplepdl.Process#getName <em>Name</em>}</li>
 *   <li>{@link simplepdl.Process#getProcessElements <em>Process Elements</em>}</li>
 *   <li>{@link simplepdl.Process#getRessources <em>Ressources</em>}</li>
 * </ul>
 *
 * @see simplepdl.SimplepdlPackage#getProcess()
 * @model
 * @generated
 */
public interface Process extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see simplepdl.SimplepdlPackage#getProcess_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link simplepdl.Process#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Process Elements</b></em>' containment reference list.
	 * The list contents are of type {@link simplepdl.ProcessElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process Elements</em>' containment reference list.
	 * @see simplepdl.SimplepdlPackage#getProcess_ProcessElements()
	 * @model containment="true"
	 * @generated
	 */
	EList<ProcessElement> getProcessElements();

	/**
	 * Returns the value of the '<em><b>Ressources</b></em>' containment reference list.
	 * The list contents are of type {@link simplepdl.Ressource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ressources</em>' containment reference list.
	 * @see simplepdl.SimplepdlPackage#getProcess_Ressources()
	 * @model containment="true"
	 * @generated
	 */
	EList<Ressource> getRessources();

	/**
	* Returns the value of the '<em><b>Work Definitions</b></em>' containment reference list.
	* The list contents are of type {@link simplepdl.WorkDefinition}.
	* <!-- begin-user-doc -->
	* <!-- end-user-doc -->
	* @return the value of the '<em>Work Definitions</em>' containment reference list.
	* @see simplepdl.SimplepdlPackage#getProcess_WorkDefinitions()
	* @model containment="true" required="true"
	* @generated
	**/
	
EList<WorkDefinition> getWorkDefinition();

/**
* Returns the value of the '<em><b>Work Sequences</b></em>' containment reference list.
* The list contents are of type {@link simplepdl.WorkSequence}.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @return the value of the '<em>Work Sequences</em>' containment reference list.
* @see simplepdl.SimplepdlPackage#getProcess_WorkSequences()
* @model containment="true"
* * @generated
*/
EList<WorkSequence> getWorkSequence();

} // Process
