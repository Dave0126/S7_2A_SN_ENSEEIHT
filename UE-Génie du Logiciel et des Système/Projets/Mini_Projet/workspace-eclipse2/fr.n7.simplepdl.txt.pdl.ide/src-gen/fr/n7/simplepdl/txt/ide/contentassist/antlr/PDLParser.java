/*
 * generated by Xtext 2.23.0
 */
package fr.n7.simplepdl.txt.ide.contentassist.antlr;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.n7.simplepdl.txt.ide.contentassist.antlr.internal.InternalPDLParser;
import fr.n7.simplepdl.txt.services.PDLGrammarAccess;
import java.util.Map;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.AbstractContentAssistParser;

public class PDLParser extends AbstractContentAssistParser {

	@Singleton
	public static final class NameMappings {
		
		private final Map<AbstractElement, String> mappings;
		
		@Inject
		public NameMappings(PDLGrammarAccess grammarAccess) {
			ImmutableMap.Builder<AbstractElement, String> builder = ImmutableMap.builder();
			init(builder, grammarAccess);
			this.mappings = builder.build();
		}
		
		public String getRuleName(AbstractElement element) {
			return mappings.get(element);
		}
		
		private static void init(ImmutableMap.Builder<AbstractElement, String> builder, PDLGrammarAccess grammarAccess) {
			builder.put(grammarAccess.getProcessElementAccess().getAlternatives(), "rule__ProcessElement__Alternatives");
			builder.put(grammarAccess.getEStringAccess().getAlternatives(), "rule__EString__Alternatives");
			builder.put(grammarAccess.getWorkSequenceTypeAccess().getAlternatives(), "rule__WorkSequenceType__Alternatives");
			builder.put(grammarAccess.getProcessAccess().getGroup(), "rule__Process__Group__0");
			builder.put(grammarAccess.getProcessAccess().getGroup_4(), "rule__Process__Group_4__0");
			builder.put(grammarAccess.getProcessAccess().getGroup_4_3(), "rule__Process__Group_4_3__0");
			builder.put(grammarAccess.getProcessAccess().getGroup_5(), "rule__Process__Group_5__0");
			builder.put(grammarAccess.getProcessAccess().getGroup_5_3(), "rule__Process__Group_5_3__0");
			builder.put(grammarAccess.getRessourceAccess().getGroup(), "rule__Ressource__Group__0");
			builder.put(grammarAccess.getRessourceAccess().getGroup_4(), "rule__Ressource__Group_4__0");
			builder.put(grammarAccess.getWorkDefinitionAccess().getGroup(), "rule__WorkDefinition__Group__0");
			builder.put(grammarAccess.getWorkDefinitionAccess().getGroup_4(), "rule__WorkDefinition__Group_4__0");
			builder.put(grammarAccess.getWorkDefinitionAccess().getGroup_4_3(), "rule__WorkDefinition__Group_4_3__0");
			builder.put(grammarAccess.getWorkDefinitionAccess().getGroup_5(), "rule__WorkDefinition__Group_5__0");
			builder.put(grammarAccess.getWorkDefinitionAccess().getGroup_5_3(), "rule__WorkDefinition__Group_5_3__0");
			builder.put(grammarAccess.getWorkDefinitionAccess().getGroup_6(), "rule__WorkDefinition__Group_6__0");
			builder.put(grammarAccess.getWorkDefinitionAccess().getGroup_6_3(), "rule__WorkDefinition__Group_6_3__0");
			builder.put(grammarAccess.getWorkSequenceAccess().getGroup(), "rule__WorkSequence__Group__0");
			builder.put(grammarAccess.getGuidanceAccess().getGroup(), "rule__Guidance__Group__0");
			builder.put(grammarAccess.getGuidanceAccess().getGroup_4(), "rule__Guidance__Group_4__0");
			builder.put(grammarAccess.getGuidanceAccess().getGroup_4_3(), "rule__Guidance__Group_4_3__0");
			builder.put(grammarAccess.getParametreAccess().getGroup(), "rule__Parametre__Group__0");
			builder.put(grammarAccess.getParametreAccess().getGroup_2(), "rule__Parametre__Group_2__0");
			builder.put(grammarAccess.getEIntAccess().getGroup(), "rule__EInt__Group__0");
			builder.put(grammarAccess.getProcessAccess().getNameAssignment_2(), "rule__Process__NameAssignment_2");
			builder.put(grammarAccess.getProcessAccess().getProcessElementsAssignment_4_2(), "rule__Process__ProcessElementsAssignment_4_2");
			builder.put(grammarAccess.getProcessAccess().getProcessElementsAssignment_4_3_1(), "rule__Process__ProcessElementsAssignment_4_3_1");
			builder.put(grammarAccess.getProcessAccess().getRessourcesAssignment_5_2(), "rule__Process__RessourcesAssignment_5_2");
			builder.put(grammarAccess.getProcessAccess().getRessourcesAssignment_5_3_1(), "rule__Process__RessourcesAssignment_5_3_1");
			builder.put(grammarAccess.getRessourceAccess().getNameAssignment_3(), "rule__Ressource__NameAssignment_3");
			builder.put(grammarAccess.getRessourceAccess().getNbOccuranceAssignment_4_1(), "rule__Ressource__NbOccuranceAssignment_4_1");
			builder.put(grammarAccess.getWorkDefinitionAccess().getNameAssignment_2(), "rule__WorkDefinition__NameAssignment_2");
			builder.put(grammarAccess.getWorkDefinitionAccess().getLinksToPredecessorsAssignment_4_2(), "rule__WorkDefinition__LinksToPredecessorsAssignment_4_2");
			builder.put(grammarAccess.getWorkDefinitionAccess().getLinksToPredecessorsAssignment_4_3_1(), "rule__WorkDefinition__LinksToPredecessorsAssignment_4_3_1");
			builder.put(grammarAccess.getWorkDefinitionAccess().getLinksToSuccessorsAssignment_5_2(), "rule__WorkDefinition__LinksToSuccessorsAssignment_5_2");
			builder.put(grammarAccess.getWorkDefinitionAccess().getLinksToSuccessorsAssignment_5_3_1(), "rule__WorkDefinition__LinksToSuccessorsAssignment_5_3_1");
			builder.put(grammarAccess.getWorkDefinitionAccess().getParametresAssignment_6_2(), "rule__WorkDefinition__ParametresAssignment_6_2");
			builder.put(grammarAccess.getWorkDefinitionAccess().getParametresAssignment_6_3_1(), "rule__WorkDefinition__ParametresAssignment_6_3_1");
			builder.put(grammarAccess.getWorkSequenceAccess().getLinkTypeAssignment_3(), "rule__WorkSequence__LinkTypeAssignment_3");
			builder.put(grammarAccess.getWorkSequenceAccess().getPredecessorAssignment_5(), "rule__WorkSequence__PredecessorAssignment_5");
			builder.put(grammarAccess.getWorkSequenceAccess().getSuccessorAssignment_7(), "rule__WorkSequence__SuccessorAssignment_7");
			builder.put(grammarAccess.getGuidanceAccess().getTextAssignment_3(), "rule__Guidance__TextAssignment_3");
			builder.put(grammarAccess.getGuidanceAccess().getElementsAssignment_4_2(), "rule__Guidance__ElementsAssignment_4_2");
			builder.put(grammarAccess.getGuidanceAccess().getElementsAssignment_4_3_1(), "rule__Guidance__ElementsAssignment_4_3_1");
			builder.put(grammarAccess.getParametreAccess().getQteAssignment_2_1(), "rule__Parametre__QteAssignment_2_1");
			builder.put(grammarAccess.getParametreAccess().getRessourceAssignment_4(), "rule__Parametre__RessourceAssignment_4");
		}
	}
	
	@Inject
	private NameMappings nameMappings;

	@Inject
	private PDLGrammarAccess grammarAccess;

	@Override
	protected InternalPDLParser createParser() {
		InternalPDLParser result = new InternalPDLParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}

	@Override
	protected String getRuleName(AbstractElement element) {
		return nameMappings.getRuleName(element);
	}

	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}

	public PDLGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(PDLGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
	public NameMappings getNameMappings() {
		return nameMappings;
	}
	
	public void setNameMappings(NameMappings nameMappings) {
		this.nameMappings = nameMappings;
	}
}
