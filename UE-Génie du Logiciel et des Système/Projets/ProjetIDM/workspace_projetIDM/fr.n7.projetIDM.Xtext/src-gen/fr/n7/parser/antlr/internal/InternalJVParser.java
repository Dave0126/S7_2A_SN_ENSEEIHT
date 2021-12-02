package fr.n7.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import fr.n7.services.JVGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalJVParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'game'", "'{'", "'inventorySize = '", "'}'", "'description {'", "'optionalText {'", "'condition = '", "'place {'", "'start = '", "'finish = '", "'prizes = '", "'paths = '", "'visible = '", "'condition ='", "'ObjectCondition {'", "'amount = '", "'amount >= '", "'amount <= '", "'KnowledgeCondition {'", "'Person {'", "'mandatory = '", "'Action {'", "'displayCondition = '", "'endCondition = '", "'consumedObject = '", "'Knowledge {'", "'Path {'", "'open = '", "'InventoryObject {'", "'transformationCondition = '", "'ObjectAfterTransformation = '", "'size = '", "'true'", "'false'"
    };
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int RULE_ID=4;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__44=44;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalJVParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalJVParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalJVParser.tokenNames; }
    public String getGrammarFileName() { return "InternalJV.g"; }



     	private JVGrammarAccess grammarAccess;

        public InternalJVParser(TokenStream input, JVGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Game";
       	}

       	@Override
       	protected JVGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleGame"
    // InternalJV.g:65:1: entryRuleGame returns [EObject current=null] : iv_ruleGame= ruleGame EOF ;
    public final EObject entryRuleGame() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGame = null;


        try {
            // InternalJV.g:65:45: (iv_ruleGame= ruleGame EOF )
            // InternalJV.g:66:2: iv_ruleGame= ruleGame EOF
            {
             newCompositeNode(grammarAccess.getGameRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGame=ruleGame();

            state._fsp--;

             current =iv_ruleGame; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGame"


    // $ANTLR start "ruleGame"
    // InternalJV.g:72:1: ruleGame returns [EObject current=null] : (otherlv_0= 'game' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' otherlv_3= 'inventorySize = ' ( (lv_inventorySize_4_0= RULE_INT ) ) ( (lv_gameElements_5_0= ruleGameElement ) )* ( (lv_objects_6_0= rulePrize ) )* otherlv_7= '}' ) ;
    public final EObject ruleGame() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_inventorySize_4_0=null;
        Token otherlv_7=null;
        EObject lv_gameElements_5_0 = null;

        EObject lv_objects_6_0 = null;



        	enterRule();

        try {
            // InternalJV.g:78:2: ( (otherlv_0= 'game' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' otherlv_3= 'inventorySize = ' ( (lv_inventorySize_4_0= RULE_INT ) ) ( (lv_gameElements_5_0= ruleGameElement ) )* ( (lv_objects_6_0= rulePrize ) )* otherlv_7= '}' ) )
            // InternalJV.g:79:2: (otherlv_0= 'game' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' otherlv_3= 'inventorySize = ' ( (lv_inventorySize_4_0= RULE_INT ) ) ( (lv_gameElements_5_0= ruleGameElement ) )* ( (lv_objects_6_0= rulePrize ) )* otherlv_7= '}' )
            {
            // InternalJV.g:79:2: (otherlv_0= 'game' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' otherlv_3= 'inventorySize = ' ( (lv_inventorySize_4_0= RULE_INT ) ) ( (lv_gameElements_5_0= ruleGameElement ) )* ( (lv_objects_6_0= rulePrize ) )* otherlv_7= '}' )
            // InternalJV.g:80:3: otherlv_0= 'game' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' otherlv_3= 'inventorySize = ' ( (lv_inventorySize_4_0= RULE_INT ) ) ( (lv_gameElements_5_0= ruleGameElement ) )* ( (lv_objects_6_0= rulePrize ) )* otherlv_7= '}'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getGameAccess().getGameKeyword_0());
            		
            // InternalJV.g:84:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalJV.g:85:4: (lv_name_1_0= RULE_ID )
            {
            // InternalJV.g:85:4: (lv_name_1_0= RULE_ID )
            // InternalJV.g:86:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_name_1_0, grammarAccess.getGameAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGameRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_5); 

            			newLeafNode(otherlv_2, grammarAccess.getGameAccess().getLeftCurlyBracketKeyword_2());
            		
            otherlv_3=(Token)match(input,13,FOLLOW_6); 

            			newLeafNode(otherlv_3, grammarAccess.getGameAccess().getInventorySizeKeyword_3());
            		
            // InternalJV.g:110:3: ( (lv_inventorySize_4_0= RULE_INT ) )
            // InternalJV.g:111:4: (lv_inventorySize_4_0= RULE_INT )
            {
            // InternalJV.g:111:4: (lv_inventorySize_4_0= RULE_INT )
            // InternalJV.g:112:5: lv_inventorySize_4_0= RULE_INT
            {
            lv_inventorySize_4_0=(Token)match(input,RULE_INT,FOLLOW_7); 

            					newLeafNode(lv_inventorySize_4_0, grammarAccess.getGameAccess().getInventorySizeINTTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGameRule());
            					}
            					setWithLastConsumed(
            						current,
            						"inventorySize",
            						lv_inventorySize_4_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            // InternalJV.g:128:3: ( (lv_gameElements_5_0= ruleGameElement ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==18||LA1_0==37) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalJV.g:129:4: (lv_gameElements_5_0= ruleGameElement )
            	    {
            	    // InternalJV.g:129:4: (lv_gameElements_5_0= ruleGameElement )
            	    // InternalJV.g:130:5: lv_gameElements_5_0= ruleGameElement
            	    {

            	    					newCompositeNode(grammarAccess.getGameAccess().getGameElementsGameElementParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_7);
            	    lv_gameElements_5_0=ruleGameElement();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getGameRule());
            	    					}
            	    					add(
            	    						current,
            	    						"gameElements",
            	    						lv_gameElements_5_0,
            	    						"fr.n7.JV.GameElement");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // InternalJV.g:147:3: ( (lv_objects_6_0= rulePrize ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==36||LA2_0==39) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalJV.g:148:4: (lv_objects_6_0= rulePrize )
            	    {
            	    // InternalJV.g:148:4: (lv_objects_6_0= rulePrize )
            	    // InternalJV.g:149:5: lv_objects_6_0= rulePrize
            	    {

            	    					newCompositeNode(grammarAccess.getGameAccess().getObjectsPrizeParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_8);
            	    lv_objects_6_0=rulePrize();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getGameRule());
            	    					}
            	    					add(
            	    						current,
            	    						"objects",
            	    						lv_objects_6_0,
            	    						"fr.n7.JV.Prize");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            otherlv_7=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_7, grammarAccess.getGameAccess().getRightCurlyBracketKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGame"


    // $ANTLR start "entryRuleDescription"
    // InternalJV.g:174:1: entryRuleDescription returns [EObject current=null] : iv_ruleDescription= ruleDescription EOF ;
    public final EObject entryRuleDescription() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDescription = null;


        try {
            // InternalJV.g:174:52: (iv_ruleDescription= ruleDescription EOF )
            // InternalJV.g:175:2: iv_ruleDescription= ruleDescription EOF
            {
             newCompositeNode(grammarAccess.getDescriptionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDescription=ruleDescription();

            state._fsp--;

             current =iv_ruleDescription; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDescription"


    // $ANTLR start "ruleDescription"
    // InternalJV.g:181:1: ruleDescription returns [EObject current=null] : (otherlv_0= 'description {' ( (lv_text_1_0= RULE_STRING ) ) ( (lv_optionalText_2_0= ruleOptionalText ) )* otherlv_3= '}' ) ;
    public final EObject ruleDescription() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_text_1_0=null;
        Token otherlv_3=null;
        EObject lv_optionalText_2_0 = null;



        	enterRule();

        try {
            // InternalJV.g:187:2: ( (otherlv_0= 'description {' ( (lv_text_1_0= RULE_STRING ) ) ( (lv_optionalText_2_0= ruleOptionalText ) )* otherlv_3= '}' ) )
            // InternalJV.g:188:2: (otherlv_0= 'description {' ( (lv_text_1_0= RULE_STRING ) ) ( (lv_optionalText_2_0= ruleOptionalText ) )* otherlv_3= '}' )
            {
            // InternalJV.g:188:2: (otherlv_0= 'description {' ( (lv_text_1_0= RULE_STRING ) ) ( (lv_optionalText_2_0= ruleOptionalText ) )* otherlv_3= '}' )
            // InternalJV.g:189:3: otherlv_0= 'description {' ( (lv_text_1_0= RULE_STRING ) ) ( (lv_optionalText_2_0= ruleOptionalText ) )* otherlv_3= '}'
            {
            otherlv_0=(Token)match(input,15,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getDescriptionAccess().getDescriptionKeyword_0());
            		
            // InternalJV.g:193:3: ( (lv_text_1_0= RULE_STRING ) )
            // InternalJV.g:194:4: (lv_text_1_0= RULE_STRING )
            {
            // InternalJV.g:194:4: (lv_text_1_0= RULE_STRING )
            // InternalJV.g:195:5: lv_text_1_0= RULE_STRING
            {
            lv_text_1_0=(Token)match(input,RULE_STRING,FOLLOW_10); 

            					newLeafNode(lv_text_1_0, grammarAccess.getDescriptionAccess().getTextSTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDescriptionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"text",
            						lv_text_1_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            // InternalJV.g:211:3: ( (lv_optionalText_2_0= ruleOptionalText ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==16) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalJV.g:212:4: (lv_optionalText_2_0= ruleOptionalText )
            	    {
            	    // InternalJV.g:212:4: (lv_optionalText_2_0= ruleOptionalText )
            	    // InternalJV.g:213:5: lv_optionalText_2_0= ruleOptionalText
            	    {

            	    					newCompositeNode(grammarAccess.getDescriptionAccess().getOptionalTextOptionalTextParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_10);
            	    lv_optionalText_2_0=ruleOptionalText();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getDescriptionRule());
            	    					}
            	    					add(
            	    						current,
            	    						"optionalText",
            	    						lv_optionalText_2_0,
            	    						"fr.n7.JV.OptionalText");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            otherlv_3=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getDescriptionAccess().getRightCurlyBracketKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDescription"


    // $ANTLR start "entryRuleOptionalText"
    // InternalJV.g:238:1: entryRuleOptionalText returns [EObject current=null] : iv_ruleOptionalText= ruleOptionalText EOF ;
    public final EObject entryRuleOptionalText() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOptionalText = null;


        try {
            // InternalJV.g:238:53: (iv_ruleOptionalText= ruleOptionalText EOF )
            // InternalJV.g:239:2: iv_ruleOptionalText= ruleOptionalText EOF
            {
             newCompositeNode(grammarAccess.getOptionalTextRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOptionalText=ruleOptionalText();

            state._fsp--;

             current =iv_ruleOptionalText; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOptionalText"


    // $ANTLR start "ruleOptionalText"
    // InternalJV.g:245:1: ruleOptionalText returns [EObject current=null] : (otherlv_0= 'optionalText {' otherlv_1= 'condition = ' ( (lv_condition_2_0= ruleCondition ) )* ( (lv_text_3_0= RULE_STRING ) ) otherlv_4= '}' ) ;
    public final EObject ruleOptionalText() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_text_3_0=null;
        Token otherlv_4=null;
        EObject lv_condition_2_0 = null;



        	enterRule();

        try {
            // InternalJV.g:251:2: ( (otherlv_0= 'optionalText {' otherlv_1= 'condition = ' ( (lv_condition_2_0= ruleCondition ) )* ( (lv_text_3_0= RULE_STRING ) ) otherlv_4= '}' ) )
            // InternalJV.g:252:2: (otherlv_0= 'optionalText {' otherlv_1= 'condition = ' ( (lv_condition_2_0= ruleCondition ) )* ( (lv_text_3_0= RULE_STRING ) ) otherlv_4= '}' )
            {
            // InternalJV.g:252:2: (otherlv_0= 'optionalText {' otherlv_1= 'condition = ' ( (lv_condition_2_0= ruleCondition ) )* ( (lv_text_3_0= RULE_STRING ) ) otherlv_4= '}' )
            // InternalJV.g:253:3: otherlv_0= 'optionalText {' otherlv_1= 'condition = ' ( (lv_condition_2_0= ruleCondition ) )* ( (lv_text_3_0= RULE_STRING ) ) otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,16,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getOptionalTextAccess().getOptionalTextKeyword_0());
            		
            otherlv_1=(Token)match(input,17,FOLLOW_12); 

            			newLeafNode(otherlv_1, grammarAccess.getOptionalTextAccess().getConditionKeyword_1());
            		
            // InternalJV.g:261:3: ( (lv_condition_2_0= ruleCondition ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==25||LA4_0==29) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalJV.g:262:4: (lv_condition_2_0= ruleCondition )
            	    {
            	    // InternalJV.g:262:4: (lv_condition_2_0= ruleCondition )
            	    // InternalJV.g:263:5: lv_condition_2_0= ruleCondition
            	    {

            	    					newCompositeNode(grammarAccess.getOptionalTextAccess().getConditionConditionParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_12);
            	    lv_condition_2_0=ruleCondition();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getOptionalTextRule());
            	    					}
            	    					add(
            	    						current,
            	    						"condition",
            	    						lv_condition_2_0,
            	    						"fr.n7.JV.Condition");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // InternalJV.g:280:3: ( (lv_text_3_0= RULE_STRING ) )
            // InternalJV.g:281:4: (lv_text_3_0= RULE_STRING )
            {
            // InternalJV.g:281:4: (lv_text_3_0= RULE_STRING )
            // InternalJV.g:282:5: lv_text_3_0= RULE_STRING
            {
            lv_text_3_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

            					newLeafNode(lv_text_3_0, grammarAccess.getOptionalTextAccess().getTextSTRINGTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getOptionalTextRule());
            					}
            					setWithLastConsumed(
            						current,
            						"text",
            						lv_text_3_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getOptionalTextAccess().getRightCurlyBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOptionalText"


    // $ANTLR start "entryRuleGameElement"
    // InternalJV.g:306:1: entryRuleGameElement returns [EObject current=null] : iv_ruleGameElement= ruleGameElement EOF ;
    public final EObject entryRuleGameElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGameElement = null;


        try {
            // InternalJV.g:306:52: (iv_ruleGameElement= ruleGameElement EOF )
            // InternalJV.g:307:2: iv_ruleGameElement= ruleGameElement EOF
            {
             newCompositeNode(grammarAccess.getGameElementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGameElement=ruleGameElement();

            state._fsp--;

             current =iv_ruleGameElement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGameElement"


    // $ANTLR start "ruleGameElement"
    // InternalJV.g:313:1: ruleGameElement returns [EObject current=null] : (this_Places_0= rulePlaces | (this_Path_1= rulePath ( (lv_description_2_0= ruleDescription ) )? ) ) ;
    public final EObject ruleGameElement() throws RecognitionException {
        EObject current = null;

        EObject this_Places_0 = null;

        EObject this_Path_1 = null;

        EObject lv_description_2_0 = null;



        	enterRule();

        try {
            // InternalJV.g:319:2: ( (this_Places_0= rulePlaces | (this_Path_1= rulePath ( (lv_description_2_0= ruleDescription ) )? ) ) )
            // InternalJV.g:320:2: (this_Places_0= rulePlaces | (this_Path_1= rulePath ( (lv_description_2_0= ruleDescription ) )? ) )
            {
            // InternalJV.g:320:2: (this_Places_0= rulePlaces | (this_Path_1= rulePath ( (lv_description_2_0= ruleDescription ) )? ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==18) ) {
                alt6=1;
            }
            else if ( (LA6_0==37) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalJV.g:321:3: this_Places_0= rulePlaces
                    {

                    			newCompositeNode(grammarAccess.getGameElementAccess().getPlacesParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Places_0=rulePlaces();

                    state._fsp--;


                    			current = this_Places_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalJV.g:330:3: (this_Path_1= rulePath ( (lv_description_2_0= ruleDescription ) )? )
                    {
                    // InternalJV.g:330:3: (this_Path_1= rulePath ( (lv_description_2_0= ruleDescription ) )? )
                    // InternalJV.g:331:4: this_Path_1= rulePath ( (lv_description_2_0= ruleDescription ) )?
                    {

                    				newCompositeNode(grammarAccess.getGameElementAccess().getPathParserRuleCall_1_0());
                    			
                    pushFollow(FOLLOW_14);
                    this_Path_1=rulePath();

                    state._fsp--;


                    				current = this_Path_1;
                    				afterParserOrEnumRuleCall();
                    			
                    // InternalJV.g:339:4: ( (lv_description_2_0= ruleDescription ) )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==15) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // InternalJV.g:340:5: (lv_description_2_0= ruleDescription )
                            {
                            // InternalJV.g:340:5: (lv_description_2_0= ruleDescription )
                            // InternalJV.g:341:6: lv_description_2_0= ruleDescription
                            {

                            						newCompositeNode(grammarAccess.getGameElementAccess().getDescriptionDescriptionParserRuleCall_1_1_0());
                            					
                            pushFollow(FOLLOW_2);
                            lv_description_2_0=ruleDescription();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getGameElementRule());
                            						}
                            						set(
                            							current,
                            							"description",
                            							lv_description_2_0,
                            							"fr.n7.JV.Description");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGameElement"


    // $ANTLR start "entryRulePlaces"
    // InternalJV.g:363:1: entryRulePlaces returns [EObject current=null] : iv_rulePlaces= rulePlaces EOF ;
    public final EObject entryRulePlaces() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePlaces = null;


        try {
            // InternalJV.g:363:47: (iv_rulePlaces= rulePlaces EOF )
            // InternalJV.g:364:2: iv_rulePlaces= rulePlaces EOF
            {
             newCompositeNode(grammarAccess.getPlacesRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePlaces=rulePlaces();

            state._fsp--;

             current =iv_rulePlaces; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePlaces"


    // $ANTLR start "rulePlaces"
    // InternalJV.g:370:1: rulePlaces returns [EObject current=null] : (otherlv_0= 'place {' ( (lv_id_1_0= RULE_STRING ) ) otherlv_2= 'start = ' ( (lv_start_3_0= ruleBOOLEAN ) ) otherlv_4= 'finish = ' ( (lv_finish_5_0= ruleBOOLEAN ) ) ( (lv_persons_6_0= rulePerson ) )* (otherlv_7= 'prizes = ' ( (lv_prizesID_8_0= RULE_STRING ) ) )* (otherlv_9= 'paths = ' ( (lv_pathsID_10_0= RULE_STRING ) ) )* otherlv_11= '}' ) ;
    public final EObject rulePlaces() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_id_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        Token lv_prizesID_8_0=null;
        Token otherlv_9=null;
        Token lv_pathsID_10_0=null;
        Token otherlv_11=null;
        Enumerator lv_start_3_0 = null;

        Enumerator lv_finish_5_0 = null;

        EObject lv_persons_6_0 = null;



        	enterRule();

        try {
            // InternalJV.g:376:2: ( (otherlv_0= 'place {' ( (lv_id_1_0= RULE_STRING ) ) otherlv_2= 'start = ' ( (lv_start_3_0= ruleBOOLEAN ) ) otherlv_4= 'finish = ' ( (lv_finish_5_0= ruleBOOLEAN ) ) ( (lv_persons_6_0= rulePerson ) )* (otherlv_7= 'prizes = ' ( (lv_prizesID_8_0= RULE_STRING ) ) )* (otherlv_9= 'paths = ' ( (lv_pathsID_10_0= RULE_STRING ) ) )* otherlv_11= '}' ) )
            // InternalJV.g:377:2: (otherlv_0= 'place {' ( (lv_id_1_0= RULE_STRING ) ) otherlv_2= 'start = ' ( (lv_start_3_0= ruleBOOLEAN ) ) otherlv_4= 'finish = ' ( (lv_finish_5_0= ruleBOOLEAN ) ) ( (lv_persons_6_0= rulePerson ) )* (otherlv_7= 'prizes = ' ( (lv_prizesID_8_0= RULE_STRING ) ) )* (otherlv_9= 'paths = ' ( (lv_pathsID_10_0= RULE_STRING ) ) )* otherlv_11= '}' )
            {
            // InternalJV.g:377:2: (otherlv_0= 'place {' ( (lv_id_1_0= RULE_STRING ) ) otherlv_2= 'start = ' ( (lv_start_3_0= ruleBOOLEAN ) ) otherlv_4= 'finish = ' ( (lv_finish_5_0= ruleBOOLEAN ) ) ( (lv_persons_6_0= rulePerson ) )* (otherlv_7= 'prizes = ' ( (lv_prizesID_8_0= RULE_STRING ) ) )* (otherlv_9= 'paths = ' ( (lv_pathsID_10_0= RULE_STRING ) ) )* otherlv_11= '}' )
            // InternalJV.g:378:3: otherlv_0= 'place {' ( (lv_id_1_0= RULE_STRING ) ) otherlv_2= 'start = ' ( (lv_start_3_0= ruleBOOLEAN ) ) otherlv_4= 'finish = ' ( (lv_finish_5_0= ruleBOOLEAN ) ) ( (lv_persons_6_0= rulePerson ) )* (otherlv_7= 'prizes = ' ( (lv_prizesID_8_0= RULE_STRING ) ) )* (otherlv_9= 'paths = ' ( (lv_pathsID_10_0= RULE_STRING ) ) )* otherlv_11= '}'
            {
            otherlv_0=(Token)match(input,18,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getPlacesAccess().getPlaceKeyword_0());
            		
            // InternalJV.g:382:3: ( (lv_id_1_0= RULE_STRING ) )
            // InternalJV.g:383:4: (lv_id_1_0= RULE_STRING )
            {
            // InternalJV.g:383:4: (lv_id_1_0= RULE_STRING )
            // InternalJV.g:384:5: lv_id_1_0= RULE_STRING
            {
            lv_id_1_0=(Token)match(input,RULE_STRING,FOLLOW_15); 

            					newLeafNode(lv_id_1_0, grammarAccess.getPlacesAccess().getIdSTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPlacesRule());
            					}
            					setWithLastConsumed(
            						current,
            						"id",
            						lv_id_1_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_2=(Token)match(input,19,FOLLOW_16); 

            			newLeafNode(otherlv_2, grammarAccess.getPlacesAccess().getStartKeyword_2());
            		
            // InternalJV.g:404:3: ( (lv_start_3_0= ruleBOOLEAN ) )
            // InternalJV.g:405:4: (lv_start_3_0= ruleBOOLEAN )
            {
            // InternalJV.g:405:4: (lv_start_3_0= ruleBOOLEAN )
            // InternalJV.g:406:5: lv_start_3_0= ruleBOOLEAN
            {

            					newCompositeNode(grammarAccess.getPlacesAccess().getStartBOOLEANEnumRuleCall_3_0());
            				
            pushFollow(FOLLOW_17);
            lv_start_3_0=ruleBOOLEAN();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPlacesRule());
            					}
            					set(
            						current,
            						"start",
            						lv_start_3_0,
            						"fr.n7.JV.BOOLEAN");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,20,FOLLOW_16); 

            			newLeafNode(otherlv_4, grammarAccess.getPlacesAccess().getFinishKeyword_4());
            		
            // InternalJV.g:427:3: ( (lv_finish_5_0= ruleBOOLEAN ) )
            // InternalJV.g:428:4: (lv_finish_5_0= ruleBOOLEAN )
            {
            // InternalJV.g:428:4: (lv_finish_5_0= ruleBOOLEAN )
            // InternalJV.g:429:5: lv_finish_5_0= ruleBOOLEAN
            {

            					newCompositeNode(grammarAccess.getPlacesAccess().getFinishBOOLEANEnumRuleCall_5_0());
            				
            pushFollow(FOLLOW_18);
            lv_finish_5_0=ruleBOOLEAN();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPlacesRule());
            					}
            					set(
            						current,
            						"finish",
            						lv_finish_5_0,
            						"fr.n7.JV.BOOLEAN");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalJV.g:446:3: ( (lv_persons_6_0= rulePerson ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==30) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalJV.g:447:4: (lv_persons_6_0= rulePerson )
            	    {
            	    // InternalJV.g:447:4: (lv_persons_6_0= rulePerson )
            	    // InternalJV.g:448:5: lv_persons_6_0= rulePerson
            	    {

            	    					newCompositeNode(grammarAccess.getPlacesAccess().getPersonsPersonParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_18);
            	    lv_persons_6_0=rulePerson();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getPlacesRule());
            	    					}
            	    					add(
            	    						current,
            	    						"persons",
            	    						lv_persons_6_0,
            	    						"fr.n7.JV.Person");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            // InternalJV.g:465:3: (otherlv_7= 'prizes = ' ( (lv_prizesID_8_0= RULE_STRING ) ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==21) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalJV.g:466:4: otherlv_7= 'prizes = ' ( (lv_prizesID_8_0= RULE_STRING ) )
            	    {
            	    otherlv_7=(Token)match(input,21,FOLLOW_9); 

            	    				newLeafNode(otherlv_7, grammarAccess.getPlacesAccess().getPrizesKeyword_7_0());
            	    			
            	    // InternalJV.g:470:4: ( (lv_prizesID_8_0= RULE_STRING ) )
            	    // InternalJV.g:471:5: (lv_prizesID_8_0= RULE_STRING )
            	    {
            	    // InternalJV.g:471:5: (lv_prizesID_8_0= RULE_STRING )
            	    // InternalJV.g:472:6: lv_prizesID_8_0= RULE_STRING
            	    {
            	    lv_prizesID_8_0=(Token)match(input,RULE_STRING,FOLLOW_19); 

            	    						newLeafNode(lv_prizesID_8_0, grammarAccess.getPlacesAccess().getPrizesIDSTRINGTerminalRuleCall_7_1_0());
            	    					

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getPlacesRule());
            	    						}
            	    						addWithLastConsumed(
            	    							current,
            	    							"prizesID",
            	    							lv_prizesID_8_0,
            	    							"org.eclipse.xtext.common.Terminals.STRING");
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // InternalJV.g:489:3: (otherlv_9= 'paths = ' ( (lv_pathsID_10_0= RULE_STRING ) ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==22) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalJV.g:490:4: otherlv_9= 'paths = ' ( (lv_pathsID_10_0= RULE_STRING ) )
            	    {
            	    otherlv_9=(Token)match(input,22,FOLLOW_9); 

            	    				newLeafNode(otherlv_9, grammarAccess.getPlacesAccess().getPathsKeyword_8_0());
            	    			
            	    // InternalJV.g:494:4: ( (lv_pathsID_10_0= RULE_STRING ) )
            	    // InternalJV.g:495:5: (lv_pathsID_10_0= RULE_STRING )
            	    {
            	    // InternalJV.g:495:5: (lv_pathsID_10_0= RULE_STRING )
            	    // InternalJV.g:496:6: lv_pathsID_10_0= RULE_STRING
            	    {
            	    lv_pathsID_10_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

            	    						newLeafNode(lv_pathsID_10_0, grammarAccess.getPlacesAccess().getPathsIDSTRINGTerminalRuleCall_8_1_0());
            	    					

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getPlacesRule());
            	    						}
            	    						addWithLastConsumed(
            	    							current,
            	    							"pathsID",
            	    							lv_pathsID_10_0,
            	    							"org.eclipse.xtext.common.Terminals.STRING");
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            otherlv_11=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_11, grammarAccess.getPlacesAccess().getRightCurlyBracketKeyword_9());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePlaces"


    // $ANTLR start "entryRulePrize"
    // InternalJV.g:521:1: entryRulePrize returns [EObject current=null] : iv_rulePrize= rulePrize EOF ;
    public final EObject entryRulePrize() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrize = null;


        try {
            // InternalJV.g:521:46: (iv_rulePrize= rulePrize EOF )
            // InternalJV.g:522:2: iv_rulePrize= rulePrize EOF
            {
             newCompositeNode(grammarAccess.getPrizeRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePrize=rulePrize();

            state._fsp--;

             current =iv_rulePrize; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePrize"


    // $ANTLR start "rulePrize"
    // InternalJV.g:528:1: rulePrize returns [EObject current=null] : (this_InventoryObject_0= ruleInventoryObject | (this_Knowledge_1= ruleKnowledge (otherlv_2= 'visible = ' ( (lv_visible_3_0= ruleCondition ) ) )* (otherlv_4= 'condition =' ( (lv_condition_5_0= ruleCondition ) ) )* ) ) ;
    public final EObject rulePrize() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_InventoryObject_0 = null;

        EObject this_Knowledge_1 = null;

        EObject lv_visible_3_0 = null;

        EObject lv_condition_5_0 = null;



        	enterRule();

        try {
            // InternalJV.g:534:2: ( (this_InventoryObject_0= ruleInventoryObject | (this_Knowledge_1= ruleKnowledge (otherlv_2= 'visible = ' ( (lv_visible_3_0= ruleCondition ) ) )* (otherlv_4= 'condition =' ( (lv_condition_5_0= ruleCondition ) ) )* ) ) )
            // InternalJV.g:535:2: (this_InventoryObject_0= ruleInventoryObject | (this_Knowledge_1= ruleKnowledge (otherlv_2= 'visible = ' ( (lv_visible_3_0= ruleCondition ) ) )* (otherlv_4= 'condition =' ( (lv_condition_5_0= ruleCondition ) ) )* ) )
            {
            // InternalJV.g:535:2: (this_InventoryObject_0= ruleInventoryObject | (this_Knowledge_1= ruleKnowledge (otherlv_2= 'visible = ' ( (lv_visible_3_0= ruleCondition ) ) )* (otherlv_4= 'condition =' ( (lv_condition_5_0= ruleCondition ) ) )* ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==39) ) {
                alt12=1;
            }
            else if ( (LA12_0==36) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // InternalJV.g:536:3: this_InventoryObject_0= ruleInventoryObject
                    {

                    			newCompositeNode(grammarAccess.getPrizeAccess().getInventoryObjectParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_InventoryObject_0=ruleInventoryObject();

                    state._fsp--;


                    			current = this_InventoryObject_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalJV.g:545:3: (this_Knowledge_1= ruleKnowledge (otherlv_2= 'visible = ' ( (lv_visible_3_0= ruleCondition ) ) )* (otherlv_4= 'condition =' ( (lv_condition_5_0= ruleCondition ) ) )* )
                    {
                    // InternalJV.g:545:3: (this_Knowledge_1= ruleKnowledge (otherlv_2= 'visible = ' ( (lv_visible_3_0= ruleCondition ) ) )* (otherlv_4= 'condition =' ( (lv_condition_5_0= ruleCondition ) ) )* )
                    // InternalJV.g:546:4: this_Knowledge_1= ruleKnowledge (otherlv_2= 'visible = ' ( (lv_visible_3_0= ruleCondition ) ) )* (otherlv_4= 'condition =' ( (lv_condition_5_0= ruleCondition ) ) )*
                    {

                    				newCompositeNode(grammarAccess.getPrizeAccess().getKnowledgeParserRuleCall_1_0());
                    			
                    pushFollow(FOLLOW_21);
                    this_Knowledge_1=ruleKnowledge();

                    state._fsp--;


                    				current = this_Knowledge_1;
                    				afterParserOrEnumRuleCall();
                    			
                    // InternalJV.g:554:4: (otherlv_2= 'visible = ' ( (lv_visible_3_0= ruleCondition ) ) )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==23) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // InternalJV.g:555:5: otherlv_2= 'visible = ' ( (lv_visible_3_0= ruleCondition ) )
                    	    {
                    	    otherlv_2=(Token)match(input,23,FOLLOW_22); 

                    	    					newLeafNode(otherlv_2, grammarAccess.getPrizeAccess().getVisibleKeyword_1_1_0());
                    	    				
                    	    // InternalJV.g:559:5: ( (lv_visible_3_0= ruleCondition ) )
                    	    // InternalJV.g:560:6: (lv_visible_3_0= ruleCondition )
                    	    {
                    	    // InternalJV.g:560:6: (lv_visible_3_0= ruleCondition )
                    	    // InternalJV.g:561:7: lv_visible_3_0= ruleCondition
                    	    {

                    	    							newCompositeNode(grammarAccess.getPrizeAccess().getVisibleConditionParserRuleCall_1_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_21);
                    	    lv_visible_3_0=ruleCondition();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getPrizeRule());
                    	    							}
                    	    							set(
                    	    								current,
                    	    								"visible",
                    	    								lv_visible_3_0,
                    	    								"fr.n7.JV.Condition");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    // InternalJV.g:579:4: (otherlv_4= 'condition =' ( (lv_condition_5_0= ruleCondition ) ) )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==24) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // InternalJV.g:580:5: otherlv_4= 'condition =' ( (lv_condition_5_0= ruleCondition ) )
                    	    {
                    	    otherlv_4=(Token)match(input,24,FOLLOW_22); 

                    	    					newLeafNode(otherlv_4, grammarAccess.getPrizeAccess().getConditionKeyword_1_2_0());
                    	    				
                    	    // InternalJV.g:584:5: ( (lv_condition_5_0= ruleCondition ) )
                    	    // InternalJV.g:585:6: (lv_condition_5_0= ruleCondition )
                    	    {
                    	    // InternalJV.g:585:6: (lv_condition_5_0= ruleCondition )
                    	    // InternalJV.g:586:7: lv_condition_5_0= ruleCondition
                    	    {

                    	    							newCompositeNode(grammarAccess.getPrizeAccess().getConditionConditionParserRuleCall_1_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_23);
                    	    lv_condition_5_0=ruleCondition();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getPrizeRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"condition",
                    	    								lv_condition_5_0,
                    	    								"fr.n7.JV.Condition");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePrize"


    // $ANTLR start "entryRuleCondition"
    // InternalJV.g:609:1: entryRuleCondition returns [EObject current=null] : iv_ruleCondition= ruleCondition EOF ;
    public final EObject entryRuleCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCondition = null;


        try {
            // InternalJV.g:609:50: (iv_ruleCondition= ruleCondition EOF )
            // InternalJV.g:610:2: iv_ruleCondition= ruleCondition EOF
            {
             newCompositeNode(grammarAccess.getConditionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCondition=ruleCondition();

            state._fsp--;

             current =iv_ruleCondition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCondition"


    // $ANTLR start "ruleCondition"
    // InternalJV.g:616:1: ruleCondition returns [EObject current=null] : (this_KnowledgeCondition_0= ruleKnowledgeCondition | this_ObjectCondition_1= ruleObjectCondition ) ;
    public final EObject ruleCondition() throws RecognitionException {
        EObject current = null;

        EObject this_KnowledgeCondition_0 = null;

        EObject this_ObjectCondition_1 = null;



        	enterRule();

        try {
            // InternalJV.g:622:2: ( (this_KnowledgeCondition_0= ruleKnowledgeCondition | this_ObjectCondition_1= ruleObjectCondition ) )
            // InternalJV.g:623:2: (this_KnowledgeCondition_0= ruleKnowledgeCondition | this_ObjectCondition_1= ruleObjectCondition )
            {
            // InternalJV.g:623:2: (this_KnowledgeCondition_0= ruleKnowledgeCondition | this_ObjectCondition_1= ruleObjectCondition )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==29) ) {
                alt13=1;
            }
            else if ( (LA13_0==25) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // InternalJV.g:624:3: this_KnowledgeCondition_0= ruleKnowledgeCondition
                    {

                    			newCompositeNode(grammarAccess.getConditionAccess().getKnowledgeConditionParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_KnowledgeCondition_0=ruleKnowledgeCondition();

                    state._fsp--;


                    			current = this_KnowledgeCondition_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalJV.g:633:3: this_ObjectCondition_1= ruleObjectCondition
                    {

                    			newCompositeNode(grammarAccess.getConditionAccess().getObjectConditionParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_ObjectCondition_1=ruleObjectCondition();

                    state._fsp--;


                    			current = this_ObjectCondition_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCondition"


    // $ANTLR start "entryRuleObjectCondition"
    // InternalJV.g:645:1: entryRuleObjectCondition returns [EObject current=null] : iv_ruleObjectCondition= ruleObjectCondition EOF ;
    public final EObject entryRuleObjectCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleObjectCondition = null;


        try {
            // InternalJV.g:645:56: (iv_ruleObjectCondition= ruleObjectCondition EOF )
            // InternalJV.g:646:2: iv_ruleObjectCondition= ruleObjectCondition EOF
            {
             newCompositeNode(grammarAccess.getObjectConditionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleObjectCondition=ruleObjectCondition();

            state._fsp--;

             current =iv_ruleObjectCondition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleObjectCondition"


    // $ANTLR start "ruleObjectCondition"
    // InternalJV.g:652:1: ruleObjectCondition returns [EObject current=null] : (otherlv_0= 'ObjectCondition {' ( (lv_objectRequiredID_1_0= RULE_STRING ) ) (otherlv_2= 'amount = ' ( (lv_amount_3_0= RULE_INT ) ) )? (otherlv_4= 'amount >= ' ( (lv_minAmount_5_0= RULE_INT ) ) )? (otherlv_6= 'amount <= ' ( (lv_maxAmount_7_0= RULE_INT ) ) )? otherlv_8= '}' ) ;
    public final EObject ruleObjectCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_objectRequiredID_1_0=null;
        Token otherlv_2=null;
        Token lv_amount_3_0=null;
        Token otherlv_4=null;
        Token lv_minAmount_5_0=null;
        Token otherlv_6=null;
        Token lv_maxAmount_7_0=null;
        Token otherlv_8=null;


        	enterRule();

        try {
            // InternalJV.g:658:2: ( (otherlv_0= 'ObjectCondition {' ( (lv_objectRequiredID_1_0= RULE_STRING ) ) (otherlv_2= 'amount = ' ( (lv_amount_3_0= RULE_INT ) ) )? (otherlv_4= 'amount >= ' ( (lv_minAmount_5_0= RULE_INT ) ) )? (otherlv_6= 'amount <= ' ( (lv_maxAmount_7_0= RULE_INT ) ) )? otherlv_8= '}' ) )
            // InternalJV.g:659:2: (otherlv_0= 'ObjectCondition {' ( (lv_objectRequiredID_1_0= RULE_STRING ) ) (otherlv_2= 'amount = ' ( (lv_amount_3_0= RULE_INT ) ) )? (otherlv_4= 'amount >= ' ( (lv_minAmount_5_0= RULE_INT ) ) )? (otherlv_6= 'amount <= ' ( (lv_maxAmount_7_0= RULE_INT ) ) )? otherlv_8= '}' )
            {
            // InternalJV.g:659:2: (otherlv_0= 'ObjectCondition {' ( (lv_objectRequiredID_1_0= RULE_STRING ) ) (otherlv_2= 'amount = ' ( (lv_amount_3_0= RULE_INT ) ) )? (otherlv_4= 'amount >= ' ( (lv_minAmount_5_0= RULE_INT ) ) )? (otherlv_6= 'amount <= ' ( (lv_maxAmount_7_0= RULE_INT ) ) )? otherlv_8= '}' )
            // InternalJV.g:660:3: otherlv_0= 'ObjectCondition {' ( (lv_objectRequiredID_1_0= RULE_STRING ) ) (otherlv_2= 'amount = ' ( (lv_amount_3_0= RULE_INT ) ) )? (otherlv_4= 'amount >= ' ( (lv_minAmount_5_0= RULE_INT ) ) )? (otherlv_6= 'amount <= ' ( (lv_maxAmount_7_0= RULE_INT ) ) )? otherlv_8= '}'
            {
            otherlv_0=(Token)match(input,25,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getObjectConditionAccess().getObjectConditionKeyword_0());
            		
            // InternalJV.g:664:3: ( (lv_objectRequiredID_1_0= RULE_STRING ) )
            // InternalJV.g:665:4: (lv_objectRequiredID_1_0= RULE_STRING )
            {
            // InternalJV.g:665:4: (lv_objectRequiredID_1_0= RULE_STRING )
            // InternalJV.g:666:5: lv_objectRequiredID_1_0= RULE_STRING
            {
            lv_objectRequiredID_1_0=(Token)match(input,RULE_STRING,FOLLOW_24); 

            					newLeafNode(lv_objectRequiredID_1_0, grammarAccess.getObjectConditionAccess().getObjectRequiredIDSTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getObjectConditionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"objectRequiredID",
            						lv_objectRequiredID_1_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            // InternalJV.g:682:3: (otherlv_2= 'amount = ' ( (lv_amount_3_0= RULE_INT ) ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==26) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalJV.g:683:4: otherlv_2= 'amount = ' ( (lv_amount_3_0= RULE_INT ) )
                    {
                    otherlv_2=(Token)match(input,26,FOLLOW_6); 

                    				newLeafNode(otherlv_2, grammarAccess.getObjectConditionAccess().getAmountKeyword_2_0());
                    			
                    // InternalJV.g:687:4: ( (lv_amount_3_0= RULE_INT ) )
                    // InternalJV.g:688:5: (lv_amount_3_0= RULE_INT )
                    {
                    // InternalJV.g:688:5: (lv_amount_3_0= RULE_INT )
                    // InternalJV.g:689:6: lv_amount_3_0= RULE_INT
                    {
                    lv_amount_3_0=(Token)match(input,RULE_INT,FOLLOW_25); 

                    						newLeafNode(lv_amount_3_0, grammarAccess.getObjectConditionAccess().getAmountINTTerminalRuleCall_2_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getObjectConditionRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"amount",
                    							lv_amount_3_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalJV.g:706:3: (otherlv_4= 'amount >= ' ( (lv_minAmount_5_0= RULE_INT ) ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==27) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalJV.g:707:4: otherlv_4= 'amount >= ' ( (lv_minAmount_5_0= RULE_INT ) )
                    {
                    otherlv_4=(Token)match(input,27,FOLLOW_6); 

                    				newLeafNode(otherlv_4, grammarAccess.getObjectConditionAccess().getAmountKeyword_3_0());
                    			
                    // InternalJV.g:711:4: ( (lv_minAmount_5_0= RULE_INT ) )
                    // InternalJV.g:712:5: (lv_minAmount_5_0= RULE_INT )
                    {
                    // InternalJV.g:712:5: (lv_minAmount_5_0= RULE_INT )
                    // InternalJV.g:713:6: lv_minAmount_5_0= RULE_INT
                    {
                    lv_minAmount_5_0=(Token)match(input,RULE_INT,FOLLOW_26); 

                    						newLeafNode(lv_minAmount_5_0, grammarAccess.getObjectConditionAccess().getMinAmountINTTerminalRuleCall_3_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getObjectConditionRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"minAmount",
                    							lv_minAmount_5_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalJV.g:730:3: (otherlv_6= 'amount <= ' ( (lv_maxAmount_7_0= RULE_INT ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==28) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalJV.g:731:4: otherlv_6= 'amount <= ' ( (lv_maxAmount_7_0= RULE_INT ) )
                    {
                    otherlv_6=(Token)match(input,28,FOLLOW_6); 

                    				newLeafNode(otherlv_6, grammarAccess.getObjectConditionAccess().getAmountKeyword_4_0());
                    			
                    // InternalJV.g:735:4: ( (lv_maxAmount_7_0= RULE_INT ) )
                    // InternalJV.g:736:5: (lv_maxAmount_7_0= RULE_INT )
                    {
                    // InternalJV.g:736:5: (lv_maxAmount_7_0= RULE_INT )
                    // InternalJV.g:737:6: lv_maxAmount_7_0= RULE_INT
                    {
                    lv_maxAmount_7_0=(Token)match(input,RULE_INT,FOLLOW_13); 

                    						newLeafNode(lv_maxAmount_7_0, grammarAccess.getObjectConditionAccess().getMaxAmountINTTerminalRuleCall_4_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getObjectConditionRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"maxAmount",
                    							lv_maxAmount_7_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_8=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getObjectConditionAccess().getRightCurlyBracketKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleObjectCondition"


    // $ANTLR start "entryRuleKnowledgeCondition"
    // InternalJV.g:762:1: entryRuleKnowledgeCondition returns [EObject current=null] : iv_ruleKnowledgeCondition= ruleKnowledgeCondition EOF ;
    public final EObject entryRuleKnowledgeCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKnowledgeCondition = null;


        try {
            // InternalJV.g:762:59: (iv_ruleKnowledgeCondition= ruleKnowledgeCondition EOF )
            // InternalJV.g:763:2: iv_ruleKnowledgeCondition= ruleKnowledgeCondition EOF
            {
             newCompositeNode(grammarAccess.getKnowledgeConditionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKnowledgeCondition=ruleKnowledgeCondition();

            state._fsp--;

             current =iv_ruleKnowledgeCondition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKnowledgeCondition"


    // $ANTLR start "ruleKnowledgeCondition"
    // InternalJV.g:769:1: ruleKnowledgeCondition returns [EObject current=null] : (otherlv_0= 'KnowledgeCondition {' ( (lv_knowledgeRequiredID_1_0= RULE_STRING ) ) otherlv_2= '}' ) ;
    public final EObject ruleKnowledgeCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_knowledgeRequiredID_1_0=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalJV.g:775:2: ( (otherlv_0= 'KnowledgeCondition {' ( (lv_knowledgeRequiredID_1_0= RULE_STRING ) ) otherlv_2= '}' ) )
            // InternalJV.g:776:2: (otherlv_0= 'KnowledgeCondition {' ( (lv_knowledgeRequiredID_1_0= RULE_STRING ) ) otherlv_2= '}' )
            {
            // InternalJV.g:776:2: (otherlv_0= 'KnowledgeCondition {' ( (lv_knowledgeRequiredID_1_0= RULE_STRING ) ) otherlv_2= '}' )
            // InternalJV.g:777:3: otherlv_0= 'KnowledgeCondition {' ( (lv_knowledgeRequiredID_1_0= RULE_STRING ) ) otherlv_2= '}'
            {
            otherlv_0=(Token)match(input,29,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getKnowledgeConditionAccess().getKnowledgeConditionKeyword_0());
            		
            // InternalJV.g:781:3: ( (lv_knowledgeRequiredID_1_0= RULE_STRING ) )
            // InternalJV.g:782:4: (lv_knowledgeRequiredID_1_0= RULE_STRING )
            {
            // InternalJV.g:782:4: (lv_knowledgeRequiredID_1_0= RULE_STRING )
            // InternalJV.g:783:5: lv_knowledgeRequiredID_1_0= RULE_STRING
            {
            lv_knowledgeRequiredID_1_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

            					newLeafNode(lv_knowledgeRequiredID_1_0, grammarAccess.getKnowledgeConditionAccess().getKnowledgeRequiredIDSTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getKnowledgeConditionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"knowledgeRequiredID",
            						lv_knowledgeRequiredID_1_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_2=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_2, grammarAccess.getKnowledgeConditionAccess().getRightCurlyBracketKeyword_2());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKnowledgeCondition"


    // $ANTLR start "entryRulePerson"
    // InternalJV.g:807:1: entryRulePerson returns [EObject current=null] : iv_rulePerson= rulePerson EOF ;
    public final EObject entryRulePerson() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePerson = null;


        try {
            // InternalJV.g:807:47: (iv_rulePerson= rulePerson EOF )
            // InternalJV.g:808:2: iv_rulePerson= rulePerson EOF
            {
             newCompositeNode(grammarAccess.getPersonRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePerson=rulePerson();

            state._fsp--;

             current =iv_rulePerson; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePerson"


    // $ANTLR start "rulePerson"
    // InternalJV.g:814:1: rulePerson returns [EObject current=null] : (otherlv_0= 'Person {' ( (lv_id_1_0= RULE_STRING ) ) otherlv_2= 'visible = ' ( (lv_visible_3_0= ruleBOOLEAN ) ) otherlv_4= 'mandatory = ' ( (lv_mandatory_5_0= ruleBOOLEAN ) ) ( (lv_actions_6_0= ruleAction ) )+ otherlv_7= '}' ) ;
    public final EObject rulePerson() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_id_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        Enumerator lv_visible_3_0 = null;

        Enumerator lv_mandatory_5_0 = null;

        EObject lv_actions_6_0 = null;



        	enterRule();

        try {
            // InternalJV.g:820:2: ( (otherlv_0= 'Person {' ( (lv_id_1_0= RULE_STRING ) ) otherlv_2= 'visible = ' ( (lv_visible_3_0= ruleBOOLEAN ) ) otherlv_4= 'mandatory = ' ( (lv_mandatory_5_0= ruleBOOLEAN ) ) ( (lv_actions_6_0= ruleAction ) )+ otherlv_7= '}' ) )
            // InternalJV.g:821:2: (otherlv_0= 'Person {' ( (lv_id_1_0= RULE_STRING ) ) otherlv_2= 'visible = ' ( (lv_visible_3_0= ruleBOOLEAN ) ) otherlv_4= 'mandatory = ' ( (lv_mandatory_5_0= ruleBOOLEAN ) ) ( (lv_actions_6_0= ruleAction ) )+ otherlv_7= '}' )
            {
            // InternalJV.g:821:2: (otherlv_0= 'Person {' ( (lv_id_1_0= RULE_STRING ) ) otherlv_2= 'visible = ' ( (lv_visible_3_0= ruleBOOLEAN ) ) otherlv_4= 'mandatory = ' ( (lv_mandatory_5_0= ruleBOOLEAN ) ) ( (lv_actions_6_0= ruleAction ) )+ otherlv_7= '}' )
            // InternalJV.g:822:3: otherlv_0= 'Person {' ( (lv_id_1_0= RULE_STRING ) ) otherlv_2= 'visible = ' ( (lv_visible_3_0= ruleBOOLEAN ) ) otherlv_4= 'mandatory = ' ( (lv_mandatory_5_0= ruleBOOLEAN ) ) ( (lv_actions_6_0= ruleAction ) )+ otherlv_7= '}'
            {
            otherlv_0=(Token)match(input,30,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getPersonAccess().getPersonKeyword_0());
            		
            // InternalJV.g:826:3: ( (lv_id_1_0= RULE_STRING ) )
            // InternalJV.g:827:4: (lv_id_1_0= RULE_STRING )
            {
            // InternalJV.g:827:4: (lv_id_1_0= RULE_STRING )
            // InternalJV.g:828:5: lv_id_1_0= RULE_STRING
            {
            lv_id_1_0=(Token)match(input,RULE_STRING,FOLLOW_27); 

            					newLeafNode(lv_id_1_0, grammarAccess.getPersonAccess().getIdSTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPersonRule());
            					}
            					setWithLastConsumed(
            						current,
            						"id",
            						lv_id_1_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_2=(Token)match(input,23,FOLLOW_16); 

            			newLeafNode(otherlv_2, grammarAccess.getPersonAccess().getVisibleKeyword_2());
            		
            // InternalJV.g:848:3: ( (lv_visible_3_0= ruleBOOLEAN ) )
            // InternalJV.g:849:4: (lv_visible_3_0= ruleBOOLEAN )
            {
            // InternalJV.g:849:4: (lv_visible_3_0= ruleBOOLEAN )
            // InternalJV.g:850:5: lv_visible_3_0= ruleBOOLEAN
            {

            					newCompositeNode(grammarAccess.getPersonAccess().getVisibleBOOLEANEnumRuleCall_3_0());
            				
            pushFollow(FOLLOW_28);
            lv_visible_3_0=ruleBOOLEAN();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPersonRule());
            					}
            					set(
            						current,
            						"visible",
            						lv_visible_3_0,
            						"fr.n7.JV.BOOLEAN");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,31,FOLLOW_16); 

            			newLeafNode(otherlv_4, grammarAccess.getPersonAccess().getMandatoryKeyword_4());
            		
            // InternalJV.g:871:3: ( (lv_mandatory_5_0= ruleBOOLEAN ) )
            // InternalJV.g:872:4: (lv_mandatory_5_0= ruleBOOLEAN )
            {
            // InternalJV.g:872:4: (lv_mandatory_5_0= ruleBOOLEAN )
            // InternalJV.g:873:5: lv_mandatory_5_0= ruleBOOLEAN
            {

            					newCompositeNode(grammarAccess.getPersonAccess().getMandatoryBOOLEANEnumRuleCall_5_0());
            				
            pushFollow(FOLLOW_29);
            lv_mandatory_5_0=ruleBOOLEAN();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPersonRule());
            					}
            					set(
            						current,
            						"mandatory",
            						lv_mandatory_5_0,
            						"fr.n7.JV.BOOLEAN");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalJV.g:890:3: ( (lv_actions_6_0= ruleAction ) )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==32) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalJV.g:891:4: (lv_actions_6_0= ruleAction )
            	    {
            	    // InternalJV.g:891:4: (lv_actions_6_0= ruleAction )
            	    // InternalJV.g:892:5: lv_actions_6_0= ruleAction
            	    {

            	    					newCompositeNode(grammarAccess.getPersonAccess().getActionsActionParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_30);
            	    lv_actions_6_0=ruleAction();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getPersonRule());
            	    					}
            	    					add(
            	    						current,
            	    						"actions",
            	    						lv_actions_6_0,
            	    						"fr.n7.JV.Action");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
            } while (true);

            otherlv_7=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_7, grammarAccess.getPersonAccess().getRightCurlyBracketKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePerson"


    // $ANTLR start "entryRuleAction"
    // InternalJV.g:917:1: entryRuleAction returns [EObject current=null] : iv_ruleAction= ruleAction EOF ;
    public final EObject entryRuleAction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAction = null;


        try {
            // InternalJV.g:917:47: (iv_ruleAction= ruleAction EOF )
            // InternalJV.g:918:2: iv_ruleAction= ruleAction EOF
            {
             newCompositeNode(grammarAccess.getActionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAction=ruleAction();

            state._fsp--;

             current =iv_ruleAction; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAction"


    // $ANTLR start "ruleAction"
    // InternalJV.g:924:1: ruleAction returns [EObject current=null] : (otherlv_0= 'Action {' (otherlv_1= 'displayCondition = ' ( (lv_displayCondition_2_0= ruleCondition ) ) )* (otherlv_3= 'endCondition = ' ( (lv_endCondition_4_0= ruleCondition ) ) )* (otherlv_5= 'condition = ' ( (lv_condition_6_0= ruleCondition ) ) )* (otherlv_7= 'prizes = ' ( (lv_prizesID_8_0= RULE_STRING ) ) )* (otherlv_9= 'consumedObject = ' ( (lv_consumedID_10_0= RULE_STRING ) ) )* otherlv_11= '}' ) ;
    public final EObject ruleAction() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token lv_prizesID_8_0=null;
        Token otherlv_9=null;
        Token lv_consumedID_10_0=null;
        Token otherlv_11=null;
        EObject lv_displayCondition_2_0 = null;

        EObject lv_endCondition_4_0 = null;

        EObject lv_condition_6_0 = null;



        	enterRule();

        try {
            // InternalJV.g:930:2: ( (otherlv_0= 'Action {' (otherlv_1= 'displayCondition = ' ( (lv_displayCondition_2_0= ruleCondition ) ) )* (otherlv_3= 'endCondition = ' ( (lv_endCondition_4_0= ruleCondition ) ) )* (otherlv_5= 'condition = ' ( (lv_condition_6_0= ruleCondition ) ) )* (otherlv_7= 'prizes = ' ( (lv_prizesID_8_0= RULE_STRING ) ) )* (otherlv_9= 'consumedObject = ' ( (lv_consumedID_10_0= RULE_STRING ) ) )* otherlv_11= '}' ) )
            // InternalJV.g:931:2: (otherlv_0= 'Action {' (otherlv_1= 'displayCondition = ' ( (lv_displayCondition_2_0= ruleCondition ) ) )* (otherlv_3= 'endCondition = ' ( (lv_endCondition_4_0= ruleCondition ) ) )* (otherlv_5= 'condition = ' ( (lv_condition_6_0= ruleCondition ) ) )* (otherlv_7= 'prizes = ' ( (lv_prizesID_8_0= RULE_STRING ) ) )* (otherlv_9= 'consumedObject = ' ( (lv_consumedID_10_0= RULE_STRING ) ) )* otherlv_11= '}' )
            {
            // InternalJV.g:931:2: (otherlv_0= 'Action {' (otherlv_1= 'displayCondition = ' ( (lv_displayCondition_2_0= ruleCondition ) ) )* (otherlv_3= 'endCondition = ' ( (lv_endCondition_4_0= ruleCondition ) ) )* (otherlv_5= 'condition = ' ( (lv_condition_6_0= ruleCondition ) ) )* (otherlv_7= 'prizes = ' ( (lv_prizesID_8_0= RULE_STRING ) ) )* (otherlv_9= 'consumedObject = ' ( (lv_consumedID_10_0= RULE_STRING ) ) )* otherlv_11= '}' )
            // InternalJV.g:932:3: otherlv_0= 'Action {' (otherlv_1= 'displayCondition = ' ( (lv_displayCondition_2_0= ruleCondition ) ) )* (otherlv_3= 'endCondition = ' ( (lv_endCondition_4_0= ruleCondition ) ) )* (otherlv_5= 'condition = ' ( (lv_condition_6_0= ruleCondition ) ) )* (otherlv_7= 'prizes = ' ( (lv_prizesID_8_0= RULE_STRING ) ) )* (otherlv_9= 'consumedObject = ' ( (lv_consumedID_10_0= RULE_STRING ) ) )* otherlv_11= '}'
            {
            otherlv_0=(Token)match(input,32,FOLLOW_31); 

            			newLeafNode(otherlv_0, grammarAccess.getActionAccess().getActionKeyword_0());
            		
            // InternalJV.g:936:3: (otherlv_1= 'displayCondition = ' ( (lv_displayCondition_2_0= ruleCondition ) ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==33) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalJV.g:937:4: otherlv_1= 'displayCondition = ' ( (lv_displayCondition_2_0= ruleCondition ) )
            	    {
            	    otherlv_1=(Token)match(input,33,FOLLOW_22); 

            	    				newLeafNode(otherlv_1, grammarAccess.getActionAccess().getDisplayConditionKeyword_1_0());
            	    			
            	    // InternalJV.g:941:4: ( (lv_displayCondition_2_0= ruleCondition ) )
            	    // InternalJV.g:942:5: (lv_displayCondition_2_0= ruleCondition )
            	    {
            	    // InternalJV.g:942:5: (lv_displayCondition_2_0= ruleCondition )
            	    // InternalJV.g:943:6: lv_displayCondition_2_0= ruleCondition
            	    {

            	    						newCompositeNode(grammarAccess.getActionAccess().getDisplayConditionConditionParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_31);
            	    lv_displayCondition_2_0=ruleCondition();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getActionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"displayCondition",
            	    							lv_displayCondition_2_0,
            	    							"fr.n7.JV.Condition");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

            // InternalJV.g:961:3: (otherlv_3= 'endCondition = ' ( (lv_endCondition_4_0= ruleCondition ) ) )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==34) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalJV.g:962:4: otherlv_3= 'endCondition = ' ( (lv_endCondition_4_0= ruleCondition ) )
            	    {
            	    otherlv_3=(Token)match(input,34,FOLLOW_22); 

            	    				newLeafNode(otherlv_3, grammarAccess.getActionAccess().getEndConditionKeyword_2_0());
            	    			
            	    // InternalJV.g:966:4: ( (lv_endCondition_4_0= ruleCondition ) )
            	    // InternalJV.g:967:5: (lv_endCondition_4_0= ruleCondition )
            	    {
            	    // InternalJV.g:967:5: (lv_endCondition_4_0= ruleCondition )
            	    // InternalJV.g:968:6: lv_endCondition_4_0= ruleCondition
            	    {

            	    						newCompositeNode(grammarAccess.getActionAccess().getEndConditionConditionParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_32);
            	    lv_endCondition_4_0=ruleCondition();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getActionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"endCondition",
            	    							lv_endCondition_4_0,
            	    							"fr.n7.JV.Condition");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            // InternalJV.g:986:3: (otherlv_5= 'condition = ' ( (lv_condition_6_0= ruleCondition ) ) )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==17) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalJV.g:987:4: otherlv_5= 'condition = ' ( (lv_condition_6_0= ruleCondition ) )
            	    {
            	    otherlv_5=(Token)match(input,17,FOLLOW_22); 

            	    				newLeafNode(otherlv_5, grammarAccess.getActionAccess().getConditionKeyword_3_0());
            	    			
            	    // InternalJV.g:991:4: ( (lv_condition_6_0= ruleCondition ) )
            	    // InternalJV.g:992:5: (lv_condition_6_0= ruleCondition )
            	    {
            	    // InternalJV.g:992:5: (lv_condition_6_0= ruleCondition )
            	    // InternalJV.g:993:6: lv_condition_6_0= ruleCondition
            	    {

            	    						newCompositeNode(grammarAccess.getActionAccess().getConditionConditionParserRuleCall_3_1_0());
            	    					
            	    pushFollow(FOLLOW_33);
            	    lv_condition_6_0=ruleCondition();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getActionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"condition",
            	    							lv_condition_6_0,
            	    							"fr.n7.JV.Condition");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            // InternalJV.g:1011:3: (otherlv_7= 'prizes = ' ( (lv_prizesID_8_0= RULE_STRING ) ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==21) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalJV.g:1012:4: otherlv_7= 'prizes = ' ( (lv_prizesID_8_0= RULE_STRING ) )
            	    {
            	    otherlv_7=(Token)match(input,21,FOLLOW_9); 

            	    				newLeafNode(otherlv_7, grammarAccess.getActionAccess().getPrizesKeyword_4_0());
            	    			
            	    // InternalJV.g:1016:4: ( (lv_prizesID_8_0= RULE_STRING ) )
            	    // InternalJV.g:1017:5: (lv_prizesID_8_0= RULE_STRING )
            	    {
            	    // InternalJV.g:1017:5: (lv_prizesID_8_0= RULE_STRING )
            	    // InternalJV.g:1018:6: lv_prizesID_8_0= RULE_STRING
            	    {
            	    lv_prizesID_8_0=(Token)match(input,RULE_STRING,FOLLOW_34); 

            	    						newLeafNode(lv_prizesID_8_0, grammarAccess.getActionAccess().getPrizesIDSTRINGTerminalRuleCall_4_1_0());
            	    					

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getActionRule());
            	    						}
            	    						addWithLastConsumed(
            	    							current,
            	    							"prizesID",
            	    							lv_prizesID_8_0,
            	    							"org.eclipse.xtext.common.Terminals.STRING");
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            // InternalJV.g:1035:3: (otherlv_9= 'consumedObject = ' ( (lv_consumedID_10_0= RULE_STRING ) ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==35) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalJV.g:1036:4: otherlv_9= 'consumedObject = ' ( (lv_consumedID_10_0= RULE_STRING ) )
            	    {
            	    otherlv_9=(Token)match(input,35,FOLLOW_9); 

            	    				newLeafNode(otherlv_9, grammarAccess.getActionAccess().getConsumedObjectKeyword_5_0());
            	    			
            	    // InternalJV.g:1040:4: ( (lv_consumedID_10_0= RULE_STRING ) )
            	    // InternalJV.g:1041:5: (lv_consumedID_10_0= RULE_STRING )
            	    {
            	    // InternalJV.g:1041:5: (lv_consumedID_10_0= RULE_STRING )
            	    // InternalJV.g:1042:6: lv_consumedID_10_0= RULE_STRING
            	    {
            	    lv_consumedID_10_0=(Token)match(input,RULE_STRING,FOLLOW_35); 

            	    						newLeafNode(lv_consumedID_10_0, grammarAccess.getActionAccess().getConsumedIDSTRINGTerminalRuleCall_5_1_0());
            	    					

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getActionRule());
            	    						}
            	    						addWithLastConsumed(
            	    							current,
            	    							"consumedID",
            	    							lv_consumedID_10_0,
            	    							"org.eclipse.xtext.common.Terminals.STRING");
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            otherlv_11=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_11, grammarAccess.getActionAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAction"


    // $ANTLR start "entryRuleKnowledge"
    // InternalJV.g:1067:1: entryRuleKnowledge returns [EObject current=null] : iv_ruleKnowledge= ruleKnowledge EOF ;
    public final EObject entryRuleKnowledge() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKnowledge = null;


        try {
            // InternalJV.g:1067:50: (iv_ruleKnowledge= ruleKnowledge EOF )
            // InternalJV.g:1068:2: iv_ruleKnowledge= ruleKnowledge EOF
            {
             newCompositeNode(grammarAccess.getKnowledgeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKnowledge=ruleKnowledge();

            state._fsp--;

             current =iv_ruleKnowledge; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKnowledge"


    // $ANTLR start "ruleKnowledge"
    // InternalJV.g:1074:1: ruleKnowledge returns [EObject current=null] : (otherlv_0= 'Knowledge {' ( (lv_id_1_0= RULE_STRING ) ) ( (lv_description_2_0= ruleDescription ) ) otherlv_3= '}' ) ;
    public final EObject ruleKnowledge() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_id_1_0=null;
        Token otherlv_3=null;
        EObject lv_description_2_0 = null;



        	enterRule();

        try {
            // InternalJV.g:1080:2: ( (otherlv_0= 'Knowledge {' ( (lv_id_1_0= RULE_STRING ) ) ( (lv_description_2_0= ruleDescription ) ) otherlv_3= '}' ) )
            // InternalJV.g:1081:2: (otherlv_0= 'Knowledge {' ( (lv_id_1_0= RULE_STRING ) ) ( (lv_description_2_0= ruleDescription ) ) otherlv_3= '}' )
            {
            // InternalJV.g:1081:2: (otherlv_0= 'Knowledge {' ( (lv_id_1_0= RULE_STRING ) ) ( (lv_description_2_0= ruleDescription ) ) otherlv_3= '}' )
            // InternalJV.g:1082:3: otherlv_0= 'Knowledge {' ( (lv_id_1_0= RULE_STRING ) ) ( (lv_description_2_0= ruleDescription ) ) otherlv_3= '}'
            {
            otherlv_0=(Token)match(input,36,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getKnowledgeAccess().getKnowledgeKeyword_0());
            		
            // InternalJV.g:1086:3: ( (lv_id_1_0= RULE_STRING ) )
            // InternalJV.g:1087:4: (lv_id_1_0= RULE_STRING )
            {
            // InternalJV.g:1087:4: (lv_id_1_0= RULE_STRING )
            // InternalJV.g:1088:5: lv_id_1_0= RULE_STRING
            {
            lv_id_1_0=(Token)match(input,RULE_STRING,FOLLOW_36); 

            					newLeafNode(lv_id_1_0, grammarAccess.getKnowledgeAccess().getIdSTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getKnowledgeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"id",
            						lv_id_1_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            // InternalJV.g:1104:3: ( (lv_description_2_0= ruleDescription ) )
            // InternalJV.g:1105:4: (lv_description_2_0= ruleDescription )
            {
            // InternalJV.g:1105:4: (lv_description_2_0= ruleDescription )
            // InternalJV.g:1106:5: lv_description_2_0= ruleDescription
            {

            					newCompositeNode(grammarAccess.getKnowledgeAccess().getDescriptionDescriptionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_13);
            lv_description_2_0=ruleDescription();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getKnowledgeRule());
            					}
            					set(
            						current,
            						"description",
            						lv_description_2_0,
            						"fr.n7.JV.Description");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getKnowledgeAccess().getRightCurlyBracketKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKnowledge"


    // $ANTLR start "entryRulePath"
    // InternalJV.g:1131:1: entryRulePath returns [EObject current=null] : iv_rulePath= rulePath EOF ;
    public final EObject entryRulePath() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePath = null;


        try {
            // InternalJV.g:1131:45: (iv_rulePath= rulePath EOF )
            // InternalJV.g:1132:2: iv_rulePath= rulePath EOF
            {
             newCompositeNode(grammarAccess.getPathRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePath=rulePath();

            state._fsp--;

             current =iv_rulePath; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePath"


    // $ANTLR start "rulePath"
    // InternalJV.g:1138:1: rulePath returns [EObject current=null] : (otherlv_0= 'Path {' ( (lv_id_1_0= RULE_STRING ) ) (otherlv_2= 'open = ' ( (lv_open_3_0= ruleCondition ) ) )* (otherlv_4= 'visible = ' ( (lv_visible_5_0= ruleCondition ) ) )* otherlv_6= 'mandatory = ' ( (lv_mandatory_7_0= ruleBOOLEAN ) ) (otherlv_8= 'prizes = ' ( (lv_prizesID_9_0= RULE_STRING ) ) )* (otherlv_10= 'consumedObject = ' ( (lv_consumedID_11_0= RULE_STRING ) ) )* otherlv_12= '}' ) ;
    public final EObject rulePath() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_id_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token lv_prizesID_9_0=null;
        Token otherlv_10=null;
        Token lv_consumedID_11_0=null;
        Token otherlv_12=null;
        EObject lv_open_3_0 = null;

        EObject lv_visible_5_0 = null;

        Enumerator lv_mandatory_7_0 = null;



        	enterRule();

        try {
            // InternalJV.g:1144:2: ( (otherlv_0= 'Path {' ( (lv_id_1_0= RULE_STRING ) ) (otherlv_2= 'open = ' ( (lv_open_3_0= ruleCondition ) ) )* (otherlv_4= 'visible = ' ( (lv_visible_5_0= ruleCondition ) ) )* otherlv_6= 'mandatory = ' ( (lv_mandatory_7_0= ruleBOOLEAN ) ) (otherlv_8= 'prizes = ' ( (lv_prizesID_9_0= RULE_STRING ) ) )* (otherlv_10= 'consumedObject = ' ( (lv_consumedID_11_0= RULE_STRING ) ) )* otherlv_12= '}' ) )
            // InternalJV.g:1145:2: (otherlv_0= 'Path {' ( (lv_id_1_0= RULE_STRING ) ) (otherlv_2= 'open = ' ( (lv_open_3_0= ruleCondition ) ) )* (otherlv_4= 'visible = ' ( (lv_visible_5_0= ruleCondition ) ) )* otherlv_6= 'mandatory = ' ( (lv_mandatory_7_0= ruleBOOLEAN ) ) (otherlv_8= 'prizes = ' ( (lv_prizesID_9_0= RULE_STRING ) ) )* (otherlv_10= 'consumedObject = ' ( (lv_consumedID_11_0= RULE_STRING ) ) )* otherlv_12= '}' )
            {
            // InternalJV.g:1145:2: (otherlv_0= 'Path {' ( (lv_id_1_0= RULE_STRING ) ) (otherlv_2= 'open = ' ( (lv_open_3_0= ruleCondition ) ) )* (otherlv_4= 'visible = ' ( (lv_visible_5_0= ruleCondition ) ) )* otherlv_6= 'mandatory = ' ( (lv_mandatory_7_0= ruleBOOLEAN ) ) (otherlv_8= 'prizes = ' ( (lv_prizesID_9_0= RULE_STRING ) ) )* (otherlv_10= 'consumedObject = ' ( (lv_consumedID_11_0= RULE_STRING ) ) )* otherlv_12= '}' )
            // InternalJV.g:1146:3: otherlv_0= 'Path {' ( (lv_id_1_0= RULE_STRING ) ) (otherlv_2= 'open = ' ( (lv_open_3_0= ruleCondition ) ) )* (otherlv_4= 'visible = ' ( (lv_visible_5_0= ruleCondition ) ) )* otherlv_6= 'mandatory = ' ( (lv_mandatory_7_0= ruleBOOLEAN ) ) (otherlv_8= 'prizes = ' ( (lv_prizesID_9_0= RULE_STRING ) ) )* (otherlv_10= 'consumedObject = ' ( (lv_consumedID_11_0= RULE_STRING ) ) )* otherlv_12= '}'
            {
            otherlv_0=(Token)match(input,37,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getPathAccess().getPathKeyword_0());
            		
            // InternalJV.g:1150:3: ( (lv_id_1_0= RULE_STRING ) )
            // InternalJV.g:1151:4: (lv_id_1_0= RULE_STRING )
            {
            // InternalJV.g:1151:4: (lv_id_1_0= RULE_STRING )
            // InternalJV.g:1152:5: lv_id_1_0= RULE_STRING
            {
            lv_id_1_0=(Token)match(input,RULE_STRING,FOLLOW_37); 

            					newLeafNode(lv_id_1_0, grammarAccess.getPathAccess().getIdSTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPathRule());
            					}
            					setWithLastConsumed(
            						current,
            						"id",
            						lv_id_1_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            // InternalJV.g:1168:3: (otherlv_2= 'open = ' ( (lv_open_3_0= ruleCondition ) ) )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==38) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalJV.g:1169:4: otherlv_2= 'open = ' ( (lv_open_3_0= ruleCondition ) )
            	    {
            	    otherlv_2=(Token)match(input,38,FOLLOW_22); 

            	    				newLeafNode(otherlv_2, grammarAccess.getPathAccess().getOpenKeyword_2_0());
            	    			
            	    // InternalJV.g:1173:4: ( (lv_open_3_0= ruleCondition ) )
            	    // InternalJV.g:1174:5: (lv_open_3_0= ruleCondition )
            	    {
            	    // InternalJV.g:1174:5: (lv_open_3_0= ruleCondition )
            	    // InternalJV.g:1175:6: lv_open_3_0= ruleCondition
            	    {

            	    						newCompositeNode(grammarAccess.getPathAccess().getOpenConditionParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_37);
            	    lv_open_3_0=ruleCondition();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getPathRule());
            	    						}
            	    						add(
            	    							current,
            	    							"open",
            	    							lv_open_3_0,
            	    							"fr.n7.JV.Condition");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            // InternalJV.g:1193:3: (otherlv_4= 'visible = ' ( (lv_visible_5_0= ruleCondition ) ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==23) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalJV.g:1194:4: otherlv_4= 'visible = ' ( (lv_visible_5_0= ruleCondition ) )
            	    {
            	    otherlv_4=(Token)match(input,23,FOLLOW_22); 

            	    				newLeafNode(otherlv_4, grammarAccess.getPathAccess().getVisibleKeyword_3_0());
            	    			
            	    // InternalJV.g:1198:4: ( (lv_visible_5_0= ruleCondition ) )
            	    // InternalJV.g:1199:5: (lv_visible_5_0= ruleCondition )
            	    {
            	    // InternalJV.g:1199:5: (lv_visible_5_0= ruleCondition )
            	    // InternalJV.g:1200:6: lv_visible_5_0= ruleCondition
            	    {

            	    						newCompositeNode(grammarAccess.getPathAccess().getVisibleConditionParserRuleCall_3_1_0());
            	    					
            	    pushFollow(FOLLOW_38);
            	    lv_visible_5_0=ruleCondition();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getPathRule());
            	    						}
            	    						add(
            	    							current,
            	    							"visible",
            	    							lv_visible_5_0,
            	    							"fr.n7.JV.Condition");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            otherlv_6=(Token)match(input,31,FOLLOW_16); 

            			newLeafNode(otherlv_6, grammarAccess.getPathAccess().getMandatoryKeyword_4());
            		
            // InternalJV.g:1222:3: ( (lv_mandatory_7_0= ruleBOOLEAN ) )
            // InternalJV.g:1223:4: (lv_mandatory_7_0= ruleBOOLEAN )
            {
            // InternalJV.g:1223:4: (lv_mandatory_7_0= ruleBOOLEAN )
            // InternalJV.g:1224:5: lv_mandatory_7_0= ruleBOOLEAN
            {

            					newCompositeNode(grammarAccess.getPathAccess().getMandatoryBOOLEANEnumRuleCall_5_0());
            				
            pushFollow(FOLLOW_34);
            lv_mandatory_7_0=ruleBOOLEAN();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPathRule());
            					}
            					set(
            						current,
            						"mandatory",
            						lv_mandatory_7_0,
            						"fr.n7.JV.BOOLEAN");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalJV.g:1241:3: (otherlv_8= 'prizes = ' ( (lv_prizesID_9_0= RULE_STRING ) ) )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==21) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalJV.g:1242:4: otherlv_8= 'prizes = ' ( (lv_prizesID_9_0= RULE_STRING ) )
            	    {
            	    otherlv_8=(Token)match(input,21,FOLLOW_9); 

            	    				newLeafNode(otherlv_8, grammarAccess.getPathAccess().getPrizesKeyword_6_0());
            	    			
            	    // InternalJV.g:1246:4: ( (lv_prizesID_9_0= RULE_STRING ) )
            	    // InternalJV.g:1247:5: (lv_prizesID_9_0= RULE_STRING )
            	    {
            	    // InternalJV.g:1247:5: (lv_prizesID_9_0= RULE_STRING )
            	    // InternalJV.g:1248:6: lv_prizesID_9_0= RULE_STRING
            	    {
            	    lv_prizesID_9_0=(Token)match(input,RULE_STRING,FOLLOW_34); 

            	    						newLeafNode(lv_prizesID_9_0, grammarAccess.getPathAccess().getPrizesIDSTRINGTerminalRuleCall_6_1_0());
            	    					

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getPathRule());
            	    						}
            	    						addWithLastConsumed(
            	    							current,
            	    							"prizesID",
            	    							lv_prizesID_9_0,
            	    							"org.eclipse.xtext.common.Terminals.STRING");
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

            // InternalJV.g:1265:3: (otherlv_10= 'consumedObject = ' ( (lv_consumedID_11_0= RULE_STRING ) ) )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==35) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalJV.g:1266:4: otherlv_10= 'consumedObject = ' ( (lv_consumedID_11_0= RULE_STRING ) )
            	    {
            	    otherlv_10=(Token)match(input,35,FOLLOW_9); 

            	    				newLeafNode(otherlv_10, grammarAccess.getPathAccess().getConsumedObjectKeyword_7_0());
            	    			
            	    // InternalJV.g:1270:4: ( (lv_consumedID_11_0= RULE_STRING ) )
            	    // InternalJV.g:1271:5: (lv_consumedID_11_0= RULE_STRING )
            	    {
            	    // InternalJV.g:1271:5: (lv_consumedID_11_0= RULE_STRING )
            	    // InternalJV.g:1272:6: lv_consumedID_11_0= RULE_STRING
            	    {
            	    lv_consumedID_11_0=(Token)match(input,RULE_STRING,FOLLOW_35); 

            	    						newLeafNode(lv_consumedID_11_0, grammarAccess.getPathAccess().getConsumedIDSTRINGTerminalRuleCall_7_1_0());
            	    					

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getPathRule());
            	    						}
            	    						addWithLastConsumed(
            	    							current,
            	    							"consumedID",
            	    							lv_consumedID_11_0,
            	    							"org.eclipse.xtext.common.Terminals.STRING");
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

            otherlv_12=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_12, grammarAccess.getPathAccess().getRightCurlyBracketKeyword_8());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePath"


    // $ANTLR start "entryRuleInventoryObject"
    // InternalJV.g:1297:1: entryRuleInventoryObject returns [EObject current=null] : iv_ruleInventoryObject= ruleInventoryObject EOF ;
    public final EObject entryRuleInventoryObject() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInventoryObject = null;


        try {
            // InternalJV.g:1297:56: (iv_ruleInventoryObject= ruleInventoryObject EOF )
            // InternalJV.g:1298:2: iv_ruleInventoryObject= ruleInventoryObject EOF
            {
             newCompositeNode(grammarAccess.getInventoryObjectRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInventoryObject=ruleInventoryObject();

            state._fsp--;

             current =iv_ruleInventoryObject; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInventoryObject"


    // $ANTLR start "ruleInventoryObject"
    // InternalJV.g:1304:1: ruleInventoryObject returns [EObject current=null] : (otherlv_0= 'InventoryObject {' ( (lv_id_1_0= RULE_STRING ) ) (otherlv_2= 'transformationCondition = ' ( (lv_transformationCondition_3_0= ruleCondition ) ) )* (otherlv_4= 'ObjectAfterTransformation = ' ( (lv_ObjectAfterTransformationID_5_0= RULE_STRING ) ) )? otherlv_6= 'size = ' ( (lv_size_7_0= RULE_INT ) ) ( (lv_description_8_0= ruleDescription ) ) otherlv_9= '}' ) ;
    public final EObject ruleInventoryObject() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_id_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token lv_ObjectAfterTransformationID_5_0=null;
        Token otherlv_6=null;
        Token lv_size_7_0=null;
        Token otherlv_9=null;
        EObject lv_transformationCondition_3_0 = null;

        EObject lv_description_8_0 = null;



        	enterRule();

        try {
            // InternalJV.g:1310:2: ( (otherlv_0= 'InventoryObject {' ( (lv_id_1_0= RULE_STRING ) ) (otherlv_2= 'transformationCondition = ' ( (lv_transformationCondition_3_0= ruleCondition ) ) )* (otherlv_4= 'ObjectAfterTransformation = ' ( (lv_ObjectAfterTransformationID_5_0= RULE_STRING ) ) )? otherlv_6= 'size = ' ( (lv_size_7_0= RULE_INT ) ) ( (lv_description_8_0= ruleDescription ) ) otherlv_9= '}' ) )
            // InternalJV.g:1311:2: (otherlv_0= 'InventoryObject {' ( (lv_id_1_0= RULE_STRING ) ) (otherlv_2= 'transformationCondition = ' ( (lv_transformationCondition_3_0= ruleCondition ) ) )* (otherlv_4= 'ObjectAfterTransformation = ' ( (lv_ObjectAfterTransformationID_5_0= RULE_STRING ) ) )? otherlv_6= 'size = ' ( (lv_size_7_0= RULE_INT ) ) ( (lv_description_8_0= ruleDescription ) ) otherlv_9= '}' )
            {
            // InternalJV.g:1311:2: (otherlv_0= 'InventoryObject {' ( (lv_id_1_0= RULE_STRING ) ) (otherlv_2= 'transformationCondition = ' ( (lv_transformationCondition_3_0= ruleCondition ) ) )* (otherlv_4= 'ObjectAfterTransformation = ' ( (lv_ObjectAfterTransformationID_5_0= RULE_STRING ) ) )? otherlv_6= 'size = ' ( (lv_size_7_0= RULE_INT ) ) ( (lv_description_8_0= ruleDescription ) ) otherlv_9= '}' )
            // InternalJV.g:1312:3: otherlv_0= 'InventoryObject {' ( (lv_id_1_0= RULE_STRING ) ) (otherlv_2= 'transformationCondition = ' ( (lv_transformationCondition_3_0= ruleCondition ) ) )* (otherlv_4= 'ObjectAfterTransformation = ' ( (lv_ObjectAfterTransformationID_5_0= RULE_STRING ) ) )? otherlv_6= 'size = ' ( (lv_size_7_0= RULE_INT ) ) ( (lv_description_8_0= ruleDescription ) ) otherlv_9= '}'
            {
            otherlv_0=(Token)match(input,39,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getInventoryObjectAccess().getInventoryObjectKeyword_0());
            		
            // InternalJV.g:1316:3: ( (lv_id_1_0= RULE_STRING ) )
            // InternalJV.g:1317:4: (lv_id_1_0= RULE_STRING )
            {
            // InternalJV.g:1317:4: (lv_id_1_0= RULE_STRING )
            // InternalJV.g:1318:5: lv_id_1_0= RULE_STRING
            {
            lv_id_1_0=(Token)match(input,RULE_STRING,FOLLOW_39); 

            					newLeafNode(lv_id_1_0, grammarAccess.getInventoryObjectAccess().getIdSTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getInventoryObjectRule());
            					}
            					setWithLastConsumed(
            						current,
            						"id",
            						lv_id_1_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            // InternalJV.g:1334:3: (otherlv_2= 'transformationCondition = ' ( (lv_transformationCondition_3_0= ruleCondition ) ) )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==40) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // InternalJV.g:1335:4: otherlv_2= 'transformationCondition = ' ( (lv_transformationCondition_3_0= ruleCondition ) )
            	    {
            	    otherlv_2=(Token)match(input,40,FOLLOW_22); 

            	    				newLeafNode(otherlv_2, grammarAccess.getInventoryObjectAccess().getTransformationConditionKeyword_2_0());
            	    			
            	    // InternalJV.g:1339:4: ( (lv_transformationCondition_3_0= ruleCondition ) )
            	    // InternalJV.g:1340:5: (lv_transformationCondition_3_0= ruleCondition )
            	    {
            	    // InternalJV.g:1340:5: (lv_transformationCondition_3_0= ruleCondition )
            	    // InternalJV.g:1341:6: lv_transformationCondition_3_0= ruleCondition
            	    {

            	    						newCompositeNode(grammarAccess.getInventoryObjectAccess().getTransformationConditionConditionParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_39);
            	    lv_transformationCondition_3_0=ruleCondition();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getInventoryObjectRule());
            	    						}
            	    						add(
            	    							current,
            	    							"transformationCondition",
            	    							lv_transformationCondition_3_0,
            	    							"fr.n7.JV.Condition");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

            // InternalJV.g:1359:3: (otherlv_4= 'ObjectAfterTransformation = ' ( (lv_ObjectAfterTransformationID_5_0= RULE_STRING ) ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==41) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalJV.g:1360:4: otherlv_4= 'ObjectAfterTransformation = ' ( (lv_ObjectAfterTransformationID_5_0= RULE_STRING ) )
                    {
                    otherlv_4=(Token)match(input,41,FOLLOW_9); 

                    				newLeafNode(otherlv_4, grammarAccess.getInventoryObjectAccess().getObjectAfterTransformationKeyword_3_0());
                    			
                    // InternalJV.g:1364:4: ( (lv_ObjectAfterTransformationID_5_0= RULE_STRING ) )
                    // InternalJV.g:1365:5: (lv_ObjectAfterTransformationID_5_0= RULE_STRING )
                    {
                    // InternalJV.g:1365:5: (lv_ObjectAfterTransformationID_5_0= RULE_STRING )
                    // InternalJV.g:1366:6: lv_ObjectAfterTransformationID_5_0= RULE_STRING
                    {
                    lv_ObjectAfterTransformationID_5_0=(Token)match(input,RULE_STRING,FOLLOW_40); 

                    						newLeafNode(lv_ObjectAfterTransformationID_5_0, grammarAccess.getInventoryObjectAccess().getObjectAfterTransformationIDSTRINGTerminalRuleCall_3_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getInventoryObjectRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"ObjectAfterTransformationID",
                    							lv_ObjectAfterTransformationID_5_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_6=(Token)match(input,42,FOLLOW_6); 

            			newLeafNode(otherlv_6, grammarAccess.getInventoryObjectAccess().getSizeKeyword_4());
            		
            // InternalJV.g:1387:3: ( (lv_size_7_0= RULE_INT ) )
            // InternalJV.g:1388:4: (lv_size_7_0= RULE_INT )
            {
            // InternalJV.g:1388:4: (lv_size_7_0= RULE_INT )
            // InternalJV.g:1389:5: lv_size_7_0= RULE_INT
            {
            lv_size_7_0=(Token)match(input,RULE_INT,FOLLOW_36); 

            					newLeafNode(lv_size_7_0, grammarAccess.getInventoryObjectAccess().getSizeINTTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getInventoryObjectRule());
            					}
            					setWithLastConsumed(
            						current,
            						"size",
            						lv_size_7_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            // InternalJV.g:1405:3: ( (lv_description_8_0= ruleDescription ) )
            // InternalJV.g:1406:4: (lv_description_8_0= ruleDescription )
            {
            // InternalJV.g:1406:4: (lv_description_8_0= ruleDescription )
            // InternalJV.g:1407:5: lv_description_8_0= ruleDescription
            {

            					newCompositeNode(grammarAccess.getInventoryObjectAccess().getDescriptionDescriptionParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_13);
            lv_description_8_0=ruleDescription();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getInventoryObjectRule());
            					}
            					set(
            						current,
            						"description",
            						lv_description_8_0,
            						"fr.n7.JV.Description");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_9=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_9, grammarAccess.getInventoryObjectAccess().getRightCurlyBracketKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInventoryObject"


    // $ANTLR start "ruleBOOLEAN"
    // InternalJV.g:1432:1: ruleBOOLEAN returns [Enumerator current=null] : ( (enumLiteral_0= 'true' ) | (enumLiteral_1= 'false' ) ) ;
    public final Enumerator ruleBOOLEAN() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalJV.g:1438:2: ( ( (enumLiteral_0= 'true' ) | (enumLiteral_1= 'false' ) ) )
            // InternalJV.g:1439:2: ( (enumLiteral_0= 'true' ) | (enumLiteral_1= 'false' ) )
            {
            // InternalJV.g:1439:2: ( (enumLiteral_0= 'true' ) | (enumLiteral_1= 'false' ) )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==43) ) {
                alt29=1;
            }
            else if ( (LA29_0==44) ) {
                alt29=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // InternalJV.g:1440:3: (enumLiteral_0= 'true' )
                    {
                    // InternalJV.g:1440:3: (enumLiteral_0= 'true' )
                    // InternalJV.g:1441:4: enumLiteral_0= 'true'
                    {
                    enumLiteral_0=(Token)match(input,43,FOLLOW_2); 

                    				current = grammarAccess.getBOOLEANAccess().getTRUEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getBOOLEANAccess().getTRUEEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalJV.g:1448:3: (enumLiteral_1= 'false' )
                    {
                    // InternalJV.g:1448:3: (enumLiteral_1= 'false' )
                    // InternalJV.g:1449:4: enumLiteral_1= 'false'
                    {
                    enumLiteral_1=(Token)match(input,44,FOLLOW_2); 

                    				current = grammarAccess.getBOOLEANAccess().getFALSEEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getBOOLEANAccess().getFALSEEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBOOLEAN"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x000000B000044000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000009000004000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000014000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000022000040L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000180000000000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000040604000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000604000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000404000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000001800002L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000022000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x000000001C004000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000018004000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000010004000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000100004000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000E00224000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000C00224000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000800224000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000800204000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000800004000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000004080800000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000070000000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000040000000000L});

}