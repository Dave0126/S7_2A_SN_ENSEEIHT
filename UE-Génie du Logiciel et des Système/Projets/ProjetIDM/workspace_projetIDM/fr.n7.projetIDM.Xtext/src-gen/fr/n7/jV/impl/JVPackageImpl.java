/**
 * generated by Xtext 2.23.0
 */
package fr.n7.jV.impl;

import fr.n7.jV.Action;
import fr.n7.jV.Condition;
import fr.n7.jV.Description;
import fr.n7.jV.Game;
import fr.n7.jV.GameElement;
import fr.n7.jV.InventoryObject;
import fr.n7.jV.JVFactory;
import fr.n7.jV.JVPackage;
import fr.n7.jV.Knowledge;
import fr.n7.jV.KnowledgeCondition;
import fr.n7.jV.ObjectCondition;
import fr.n7.jV.OptionalText;
import fr.n7.jV.Path;
import fr.n7.jV.Person;
import fr.n7.jV.Places;
import fr.n7.jV.Prize;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JVPackageImpl extends EPackageImpl implements JVPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass gameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass descriptionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass optionalTextEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass gameElementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass placesEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass prizeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass conditionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass objectConditionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass knowledgeConditionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass personEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass actionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass knowledgeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pathEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass inventoryObjectEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum booleanEEnum = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see fr.n7.jV.JVPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private JVPackageImpl()
  {
    super(eNS_URI, JVFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   *
   * <p>This method is used to initialize {@link JVPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static JVPackage init()
  {
    if (isInited) return (JVPackage)EPackage.Registry.INSTANCE.getEPackage(JVPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredJVPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    JVPackageImpl theJVPackage = registeredJVPackage instanceof JVPackageImpl ? (JVPackageImpl)registeredJVPackage : new JVPackageImpl();

    isInited = true;

    // Create package meta-data objects
    theJVPackage.createPackageContents();

    // Initialize created meta-data
    theJVPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theJVPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(JVPackage.eNS_URI, theJVPackage);
    return theJVPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getGame()
  {
    return gameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGame_Name()
  {
    return (EAttribute)gameEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGame_InventorySize()
  {
    return (EAttribute)gameEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGame_GameElements()
  {
    return (EReference)gameEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGame_Objects()
  {
    return (EReference)gameEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getDescription()
  {
    return descriptionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getDescription_Text()
  {
    return (EAttribute)descriptionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getDescription_OptionalText()
  {
    return (EReference)descriptionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getOptionalText()
  {
    return optionalTextEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getOptionalText_Condition()
  {
    return (EReference)optionalTextEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getOptionalText_Text()
  {
    return (EAttribute)optionalTextEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getGameElement()
  {
    return gameElementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGameElement_Id()
  {
    return (EAttribute)gameElementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGameElement_PrizesID()
  {
    return (EAttribute)gameElementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getPlaces()
  {
    return placesEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getPlaces_Start()
  {
    return (EAttribute)placesEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getPlaces_Finish()
  {
    return (EAttribute)placesEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getPlaces_Persons()
  {
    return (EReference)placesEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getPlaces_PathsID()
  {
    return (EAttribute)placesEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getPrize()
  {
    return prizeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getPrize_Id()
  {
    return (EAttribute)prizeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getPrize_Description()
  {
    return (EReference)prizeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getCondition()
  {
    return conditionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getObjectCondition()
  {
    return objectConditionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getObjectCondition_ObjectRequiredID()
  {
    return (EAttribute)objectConditionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getObjectCondition_Amount()
  {
    return (EAttribute)objectConditionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getObjectCondition_MinAmount()
  {
    return (EAttribute)objectConditionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getObjectCondition_MaxAmount()
  {
    return (EAttribute)objectConditionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getKnowledgeCondition()
  {
    return knowledgeConditionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getKnowledgeCondition_KnowledgeRequiredID()
  {
    return (EAttribute)knowledgeConditionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getPerson()
  {
    return personEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getPerson_Id()
  {
    return (EAttribute)personEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getPerson_Visible()
  {
    return (EAttribute)personEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getPerson_Mandatory()
  {
    return (EAttribute)personEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getPerson_Actions()
  {
    return (EReference)personEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getAction()
  {
    return actionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getAction_DisplayCondition()
  {
    return (EReference)actionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getAction_EndCondition()
  {
    return (EReference)actionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getAction_Condition()
  {
    return (EReference)actionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getAction_PrizesID()
  {
    return (EAttribute)actionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getAction_ConsumedID()
  {
    return (EAttribute)actionEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getKnowledge()
  {
    return knowledgeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getKnowledge_Visible()
  {
    return (EReference)knowledgeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getKnowledge_Condition()
  {
    return (EReference)knowledgeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getPath()
  {
    return pathEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getPath_Description()
  {
    return (EReference)pathEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getPath_Open()
  {
    return (EReference)pathEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getPath_Visible()
  {
    return (EReference)pathEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getPath_Mandatory()
  {
    return (EAttribute)pathEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getPath_ConsumedID()
  {
    return (EAttribute)pathEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getInventoryObject()
  {
    return inventoryObjectEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getInventoryObject_TransformationCondition()
  {
    return (EReference)inventoryObjectEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getInventoryObject_ObjectAfterTransformationID()
  {
    return (EAttribute)inventoryObjectEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getInventoryObject_Size()
  {
    return (EAttribute)inventoryObjectEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EEnum getBOOLEAN()
  {
    return booleanEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public JVFactory getJVFactory()
  {
    return (JVFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    gameEClass = createEClass(GAME);
    createEAttribute(gameEClass, GAME__NAME);
    createEAttribute(gameEClass, GAME__INVENTORY_SIZE);
    createEReference(gameEClass, GAME__GAME_ELEMENTS);
    createEReference(gameEClass, GAME__OBJECTS);

    descriptionEClass = createEClass(DESCRIPTION);
    createEAttribute(descriptionEClass, DESCRIPTION__TEXT);
    createEReference(descriptionEClass, DESCRIPTION__OPTIONAL_TEXT);

    optionalTextEClass = createEClass(OPTIONAL_TEXT);
    createEReference(optionalTextEClass, OPTIONAL_TEXT__CONDITION);
    createEAttribute(optionalTextEClass, OPTIONAL_TEXT__TEXT);

    gameElementEClass = createEClass(GAME_ELEMENT);
    createEAttribute(gameElementEClass, GAME_ELEMENT__ID);
    createEAttribute(gameElementEClass, GAME_ELEMENT__PRIZES_ID);

    placesEClass = createEClass(PLACES);
    createEAttribute(placesEClass, PLACES__START);
    createEAttribute(placesEClass, PLACES__FINISH);
    createEReference(placesEClass, PLACES__PERSONS);
    createEAttribute(placesEClass, PLACES__PATHS_ID);

    prizeEClass = createEClass(PRIZE);
    createEAttribute(prizeEClass, PRIZE__ID);
    createEReference(prizeEClass, PRIZE__DESCRIPTION);

    conditionEClass = createEClass(CONDITION);

    objectConditionEClass = createEClass(OBJECT_CONDITION);
    createEAttribute(objectConditionEClass, OBJECT_CONDITION__OBJECT_REQUIRED_ID);
    createEAttribute(objectConditionEClass, OBJECT_CONDITION__AMOUNT);
    createEAttribute(objectConditionEClass, OBJECT_CONDITION__MIN_AMOUNT);
    createEAttribute(objectConditionEClass, OBJECT_CONDITION__MAX_AMOUNT);

    knowledgeConditionEClass = createEClass(KNOWLEDGE_CONDITION);
    createEAttribute(knowledgeConditionEClass, KNOWLEDGE_CONDITION__KNOWLEDGE_REQUIRED_ID);

    personEClass = createEClass(PERSON);
    createEAttribute(personEClass, PERSON__ID);
    createEAttribute(personEClass, PERSON__VISIBLE);
    createEAttribute(personEClass, PERSON__MANDATORY);
    createEReference(personEClass, PERSON__ACTIONS);

    actionEClass = createEClass(ACTION);
    createEReference(actionEClass, ACTION__DISPLAY_CONDITION);
    createEReference(actionEClass, ACTION__END_CONDITION);
    createEReference(actionEClass, ACTION__CONDITION);
    createEAttribute(actionEClass, ACTION__PRIZES_ID);
    createEAttribute(actionEClass, ACTION__CONSUMED_ID);

    knowledgeEClass = createEClass(KNOWLEDGE);
    createEReference(knowledgeEClass, KNOWLEDGE__VISIBLE);
    createEReference(knowledgeEClass, KNOWLEDGE__CONDITION);

    pathEClass = createEClass(PATH);
    createEReference(pathEClass, PATH__DESCRIPTION);
    createEReference(pathEClass, PATH__OPEN);
    createEReference(pathEClass, PATH__VISIBLE);
    createEAttribute(pathEClass, PATH__MANDATORY);
    createEAttribute(pathEClass, PATH__CONSUMED_ID);

    inventoryObjectEClass = createEClass(INVENTORY_OBJECT);
    createEReference(inventoryObjectEClass, INVENTORY_OBJECT__TRANSFORMATION_CONDITION);
    createEAttribute(inventoryObjectEClass, INVENTORY_OBJECT__OBJECT_AFTER_TRANSFORMATION_ID);
    createEAttribute(inventoryObjectEClass, INVENTORY_OBJECT__SIZE);

    // Create enums
    booleanEEnum = createEEnum(BOOLEAN);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    placesEClass.getESuperTypes().add(this.getGameElement());
    objectConditionEClass.getESuperTypes().add(this.getCondition());
    knowledgeConditionEClass.getESuperTypes().add(this.getCondition());
    knowledgeEClass.getESuperTypes().add(this.getPrize());
    pathEClass.getESuperTypes().add(this.getGameElement());
    inventoryObjectEClass.getESuperTypes().add(this.getPrize());

    // Initialize classes and features; add operations and parameters
    initEClass(gameEClass, Game.class, "Game", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGame_Name(), ecorePackage.getEString(), "name", null, 0, 1, Game.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGame_InventorySize(), ecorePackage.getEInt(), "inventorySize", null, 0, 1, Game.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGame_GameElements(), this.getGameElement(), null, "gameElements", null, 0, -1, Game.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGame_Objects(), this.getPrize(), null, "objects", null, 0, -1, Game.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(descriptionEClass, Description.class, "Description", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDescription_Text(), ecorePackage.getEString(), "text", null, 0, 1, Description.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDescription_OptionalText(), this.getOptionalText(), null, "optionalText", null, 0, -1, Description.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(optionalTextEClass, OptionalText.class, "OptionalText", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOptionalText_Condition(), this.getCondition(), null, "condition", null, 0, -1, OptionalText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOptionalText_Text(), ecorePackage.getEString(), "text", null, 0, 1, OptionalText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(gameElementEClass, GameElement.class, "GameElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGameElement_Id(), ecorePackage.getEString(), "id", null, 0, 1, GameElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGameElement_PrizesID(), ecorePackage.getEString(), "prizesID", null, 0, -1, GameElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(placesEClass, Places.class, "Places", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPlaces_Start(), this.getBOOLEAN(), "start", null, 0, 1, Places.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPlaces_Finish(), this.getBOOLEAN(), "finish", null, 0, 1, Places.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPlaces_Persons(), this.getPerson(), null, "persons", null, 0, -1, Places.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPlaces_PathsID(), ecorePackage.getEString(), "pathsID", null, 0, -1, Places.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(prizeEClass, Prize.class, "Prize", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPrize_Id(), ecorePackage.getEString(), "id", null, 0, 1, Prize.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPrize_Description(), this.getDescription(), null, "description", null, 0, 1, Prize.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(conditionEClass, Condition.class, "Condition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(objectConditionEClass, ObjectCondition.class, "ObjectCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getObjectCondition_ObjectRequiredID(), ecorePackage.getEString(), "objectRequiredID", null, 0, 1, ObjectCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getObjectCondition_Amount(), ecorePackage.getEInt(), "amount", null, 0, 1, ObjectCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getObjectCondition_MinAmount(), ecorePackage.getEInt(), "minAmount", null, 0, 1, ObjectCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getObjectCondition_MaxAmount(), ecorePackage.getEInt(), "maxAmount", null, 0, 1, ObjectCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(knowledgeConditionEClass, KnowledgeCondition.class, "KnowledgeCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getKnowledgeCondition_KnowledgeRequiredID(), ecorePackage.getEString(), "knowledgeRequiredID", null, 0, 1, KnowledgeCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(personEClass, Person.class, "Person", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPerson_Id(), ecorePackage.getEString(), "id", null, 0, 1, Person.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPerson_Visible(), this.getBOOLEAN(), "visible", null, 0, 1, Person.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPerson_Mandatory(), this.getBOOLEAN(), "mandatory", null, 0, 1, Person.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPerson_Actions(), this.getAction(), null, "actions", null, 0, -1, Person.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(actionEClass, Action.class, "Action", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAction_DisplayCondition(), this.getCondition(), null, "displayCondition", null, 0, -1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAction_EndCondition(), this.getCondition(), null, "endCondition", null, 0, -1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAction_Condition(), this.getCondition(), null, "condition", null, 0, -1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAction_PrizesID(), ecorePackage.getEString(), "prizesID", null, 0, -1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAction_ConsumedID(), ecorePackage.getEString(), "consumedID", null, 0, -1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(knowledgeEClass, Knowledge.class, "Knowledge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getKnowledge_Visible(), this.getCondition(), null, "visible", null, 0, 1, Knowledge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getKnowledge_Condition(), this.getCondition(), null, "condition", null, 0, -1, Knowledge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pathEClass, Path.class, "Path", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPath_Description(), this.getDescription(), null, "description", null, 0, 1, Path.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPath_Open(), this.getCondition(), null, "open", null, 0, -1, Path.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPath_Visible(), this.getCondition(), null, "visible", null, 0, -1, Path.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPath_Mandatory(), this.getBOOLEAN(), "mandatory", null, 0, 1, Path.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPath_ConsumedID(), ecorePackage.getEString(), "consumedID", null, 0, -1, Path.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(inventoryObjectEClass, InventoryObject.class, "InventoryObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getInventoryObject_TransformationCondition(), this.getCondition(), null, "transformationCondition", null, 0, -1, InventoryObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getInventoryObject_ObjectAfterTransformationID(), ecorePackage.getEString(), "ObjectAfterTransformationID", null, 0, 1, InventoryObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getInventoryObject_Size(), ecorePackage.getEInt(), "size", null, 0, 1, InventoryObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(booleanEEnum, fr.n7.jV.BOOLEAN.class, "BOOLEAN");
    addEEnumLiteral(booleanEEnum, fr.n7.jV.BOOLEAN.TRUE);
    addEEnumLiteral(booleanEEnum, fr.n7.jV.BOOLEAN.FALSE);

    // Create resource
    createResource(eNS_URI);
  }

} //JVPackageImpl
