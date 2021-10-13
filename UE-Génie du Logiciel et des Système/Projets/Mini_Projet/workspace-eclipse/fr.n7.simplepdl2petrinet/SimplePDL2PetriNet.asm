<?xml version = '1.0' encoding = 'ISO-8859-1' ?>
<asm version="1.0" name="0">
	<cp>
		<constant value="SimplePDL2PetriNet"/>
		<constant value="links"/>
		<constant value="NTransientLinkSet;"/>
		<constant value="col"/>
		<constant value="J"/>
		<constant value="main"/>
		<constant value="A"/>
		<constant value="OclParametrizedType"/>
		<constant value="#native"/>
		<constant value="Collection"/>
		<constant value="J.setName(S):V"/>
		<constant value="OclSimpleType"/>
		<constant value="OclAny"/>
		<constant value="J.setElementType(J):V"/>
		<constant value="TransientLinkSet"/>
		<constant value="A.__matcher__():V"/>
		<constant value="A.__exec__():V"/>
		<constant value="self"/>
		<constant value="__resolve__"/>
		<constant value="1"/>
		<constant value="J.oclIsKindOf(J):B"/>
		<constant value="18"/>
		<constant value="NTransientLinkSet;.getLinkBySourceElement(S):QNTransientLink;"/>
		<constant value="J.oclIsUndefined():B"/>
		<constant value="15"/>
		<constant value="NTransientLink;.getTargetFromSource(J):J"/>
		<constant value="17"/>
		<constant value="30"/>
		<constant value="Sequence"/>
		<constant value="2"/>
		<constant value="A.__resolve__(J):J"/>
		<constant value="QJ.including(J):QJ"/>
		<constant value="QJ.flatten():QJ"/>
		<constant value="e"/>
		<constant value="value"/>
		<constant value="resolveTemp"/>
		<constant value="S"/>
		<constant value="NTransientLink;.getNamedTargetFromSource(JS):J"/>
		<constant value="name"/>
		<constant value="__matcher__"/>
		<constant value="A.__matchProcess2PetriNet():V"/>
		<constant value="A.__matchWorkDefinition2PetriNet():V"/>
		<constant value="__exec__"/>
		<constant value="Process2PetriNet"/>
		<constant value="NTransientLinkSet;.getLinksByRule(S):QNTransientLink;"/>
		<constant value="A.__applyProcess2PetriNet(NTransientLink;):V"/>
		<constant value="WorkDefinition2PetriNet"/>
		<constant value="A.__applyWorkDefinition2PetriNet(NTransientLink;):V"/>
		<constant value="getProcess"/>
		<constant value="Msimplepdl!ProcessElement;"/>
		<constant value="Process"/>
		<constant value="simplepdl"/>
		<constant value="J.allInstances():J"/>
		<constant value="processElements"/>
		<constant value="0"/>
		<constant value="J.includes(J):J"/>
		<constant value="B.not():B"/>
		<constant value="CJ.including(J):CJ"/>
		<constant value="J.asSequence():J"/>
		<constant value="J.first():J"/>
		<constant value="9:2-9:19"/>
		<constant value="9:2-9:34"/>
		<constant value="10:16-10:17"/>
		<constant value="10:16-10:33"/>
		<constant value="10:44-10:48"/>
		<constant value="10:16-10:49"/>
		<constant value="9:2-10:50"/>
		<constant value="9:2-11:17"/>
		<constant value="9:2-11:26"/>
		<constant value="p"/>
		<constant value="__matchProcess2PetriNet"/>
		<constant value="IN"/>
		<constant value="MMOF!Classifier;.allInstancesFrom(S):QJ"/>
		<constant value="TransientLink"/>
		<constant value="NTransientLink;.setRule(MATL!Rule;):V"/>
		<constant value="NTransientLink;.addSourceElement(SJ):V"/>
		<constant value="pn"/>
		<constant value="PetriNet"/>
		<constant value="petrinet"/>
		<constant value="NTransientLink;.addTargetElement(SJ):V"/>
		<constant value="NTransientLinkSet;.addLink2(NTransientLink;B):V"/>
		<constant value="16:5-16:43"/>
		<constant value="__applyProcess2PetriNet"/>
		<constant value="NTransientLink;"/>
		<constant value="NTransientLink;.getSourceElement(S):J"/>
		<constant value="NTransientLink;.getTargetElement(S):J"/>
		<constant value="3"/>
		<constant value="16:36-16:37"/>
		<constant value="16:36-16:42"/>
		<constant value="16:28-16:42"/>
		<constant value="link"/>
		<constant value="__matchWorkDefinition2PetriNet"/>
		<constant value="WorkDefinition"/>
		<constant value="wd"/>
		<constant value="p_ready"/>
		<constant value="Place"/>
		<constant value="24:3-27:28"/>
		<constant value="__applyWorkDefinition2PetriNet"/>
		<constant value="_ready"/>
		<constant value="J.+(J):J"/>
		<constant value="marking"/>
		<constant value="J.getProcess():J"/>
		<constant value="net"/>
		<constant value="25:13-25:15"/>
		<constant value="25:13-25:20"/>
		<constant value="25:23-25:31"/>
		<constant value="25:13-25:31"/>
		<constant value="25:5-25:31"/>
		<constant value="26:16-26:17"/>
		<constant value="26:5-26:17"/>
		<constant value="27:12-27:14"/>
		<constant value="27:12-27:27"/>
		<constant value="27:5-27:27"/>
	</cp>
	<field name="1" type="2"/>
	<field name="3" type="4"/>
	<operation name="5">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<getasm/>
			<push arg="7"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="9"/>
			<pcall arg="10"/>
			<dup/>
			<push arg="11"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="12"/>
			<pcall arg="10"/>
			<pcall arg="13"/>
			<set arg="3"/>
			<getasm/>
			<push arg="14"/>
			<push arg="8"/>
			<new/>
			<set arg="1"/>
			<getasm/>
			<pcall arg="15"/>
			<getasm/>
			<pcall arg="16"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="17" begin="0" end="24"/>
		</localvariabletable>
	</operation>
	<operation name="18">
		<context type="6"/>
		<parameters>
			<parameter name="19" type="4"/>
		</parameters>
		<code>
			<load arg="19"/>
			<getasm/>
			<get arg="3"/>
			<call arg="20"/>
			<if arg="21"/>
			<getasm/>
			<get arg="1"/>
			<load arg="19"/>
			<call arg="22"/>
			<dup/>
			<call arg="23"/>
			<if arg="24"/>
			<load arg="19"/>
			<call arg="25"/>
			<goto arg="26"/>
			<pop/>
			<load arg="19"/>
			<goto arg="27"/>
			<push arg="28"/>
			<push arg="8"/>
			<new/>
			<load arg="19"/>
			<iterate/>
			<store arg="29"/>
			<getasm/>
			<load arg="29"/>
			<call arg="30"/>
			<call arg="31"/>
			<enditerate/>
			<call arg="32"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="2" name="33" begin="23" end="27"/>
			<lve slot="0" name="17" begin="0" end="29"/>
			<lve slot="1" name="34" begin="0" end="29"/>
		</localvariabletable>
	</operation>
	<operation name="35">
		<context type="6"/>
		<parameters>
			<parameter name="19" type="4"/>
			<parameter name="29" type="36"/>
		</parameters>
		<code>
			<getasm/>
			<get arg="1"/>
			<load arg="19"/>
			<call arg="22"/>
			<load arg="19"/>
			<load arg="29"/>
			<call arg="37"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="17" begin="0" end="6"/>
			<lve slot="1" name="34" begin="0" end="6"/>
			<lve slot="2" name="38" begin="0" end="6"/>
		</localvariabletable>
	</operation>
	<operation name="39">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<getasm/>
			<pcall arg="40"/>
			<getasm/>
			<pcall arg="41"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="17" begin="0" end="3"/>
		</localvariabletable>
	</operation>
	<operation name="42">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<getasm/>
			<get arg="1"/>
			<push arg="43"/>
			<call arg="44"/>
			<iterate/>
			<store arg="19"/>
			<getasm/>
			<load arg="19"/>
			<pcall arg="45"/>
			<enditerate/>
			<getasm/>
			<get arg="1"/>
			<push arg="46"/>
			<call arg="44"/>
			<iterate/>
			<store arg="19"/>
			<getasm/>
			<load arg="19"/>
			<pcall arg="47"/>
			<enditerate/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="33" begin="5" end="8"/>
			<lve slot="1" name="33" begin="15" end="18"/>
			<lve slot="0" name="17" begin="0" end="19"/>
		</localvariabletable>
	</operation>
	<operation name="48">
		<context type="49"/>
		<parameters>
		</parameters>
		<code>
			<push arg="28"/>
			<push arg="8"/>
			<new/>
			<push arg="50"/>
			<push arg="51"/>
			<findme/>
			<call arg="52"/>
			<iterate/>
			<store arg="19"/>
			<load arg="19"/>
			<get arg="53"/>
			<load arg="54"/>
			<call arg="55"/>
			<call arg="56"/>
			<if arg="26"/>
			<load arg="19"/>
			<call arg="57"/>
			<enditerate/>
			<call arg="58"/>
			<call arg="59"/>
		</code>
		<linenumbertable>
			<lne id="60" begin="3" end="5"/>
			<lne id="61" begin="3" end="6"/>
			<lne id="62" begin="9" end="9"/>
			<lne id="63" begin="9" end="10"/>
			<lne id="64" begin="11" end="11"/>
			<lne id="65" begin="9" end="12"/>
			<lne id="66" begin="0" end="17"/>
			<lne id="67" begin="0" end="18"/>
			<lne id="68" begin="0" end="19"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="69" begin="8" end="16"/>
			<lve slot="0" name="17" begin="0" end="19"/>
		</localvariabletable>
	</operation>
	<operation name="70">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<push arg="50"/>
			<push arg="51"/>
			<findme/>
			<push arg="71"/>
			<call arg="72"/>
			<iterate/>
			<store arg="19"/>
			<getasm/>
			<get arg="1"/>
			<push arg="73"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="43"/>
			<pcall arg="74"/>
			<dup/>
			<push arg="69"/>
			<load arg="19"/>
			<pcall arg="75"/>
			<dup/>
			<push arg="76"/>
			<push arg="77"/>
			<push arg="78"/>
			<new/>
			<pcall arg="79"/>
			<pusht/>
			<pcall arg="80"/>
			<enditerate/>
		</code>
		<linenumbertable>
			<lne id="81" begin="19" end="24"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="69" begin="6" end="26"/>
			<lve slot="0" name="17" begin="0" end="27"/>
		</localvariabletable>
	</operation>
	<operation name="82">
		<context type="6"/>
		<parameters>
			<parameter name="19" type="83"/>
		</parameters>
		<code>
			<load arg="19"/>
			<push arg="69"/>
			<call arg="84"/>
			<store arg="29"/>
			<load arg="19"/>
			<push arg="76"/>
			<call arg="85"/>
			<store arg="86"/>
			<load arg="86"/>
			<dup/>
			<getasm/>
			<load arg="29"/>
			<get arg="38"/>
			<call arg="30"/>
			<set arg="38"/>
			<pop/>
		</code>
		<linenumbertable>
			<lne id="87" begin="11" end="11"/>
			<lne id="88" begin="11" end="12"/>
			<lne id="89" begin="9" end="14"/>
			<lne id="81" begin="8" end="15"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="3" name="76" begin="7" end="15"/>
			<lve slot="2" name="69" begin="3" end="15"/>
			<lve slot="0" name="17" begin="0" end="15"/>
			<lve slot="1" name="90" begin="0" end="15"/>
		</localvariabletable>
	</operation>
	<operation name="91">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<push arg="92"/>
			<push arg="51"/>
			<findme/>
			<push arg="71"/>
			<call arg="72"/>
			<iterate/>
			<store arg="19"/>
			<getasm/>
			<get arg="1"/>
			<push arg="73"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="46"/>
			<pcall arg="74"/>
			<dup/>
			<push arg="93"/>
			<load arg="19"/>
			<pcall arg="75"/>
			<dup/>
			<push arg="94"/>
			<push arg="95"/>
			<push arg="78"/>
			<new/>
			<pcall arg="79"/>
			<pusht/>
			<pcall arg="80"/>
			<enditerate/>
		</code>
		<linenumbertable>
			<lne id="96" begin="19" end="24"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="93" begin="6" end="26"/>
			<lve slot="0" name="17" begin="0" end="27"/>
		</localvariabletable>
	</operation>
	<operation name="97">
		<context type="6"/>
		<parameters>
			<parameter name="19" type="83"/>
		</parameters>
		<code>
			<load arg="19"/>
			<push arg="93"/>
			<call arg="84"/>
			<store arg="29"/>
			<load arg="19"/>
			<push arg="94"/>
			<call arg="85"/>
			<store arg="86"/>
			<load arg="86"/>
			<dup/>
			<getasm/>
			<load arg="29"/>
			<get arg="38"/>
			<push arg="98"/>
			<call arg="99"/>
			<call arg="30"/>
			<set arg="38"/>
			<dup/>
			<getasm/>
			<pushi arg="19"/>
			<call arg="30"/>
			<set arg="100"/>
			<dup/>
			<getasm/>
			<load arg="29"/>
			<call arg="101"/>
			<call arg="30"/>
			<set arg="102"/>
			<pop/>
		</code>
		<linenumbertable>
			<lne id="103" begin="11" end="11"/>
			<lne id="104" begin="11" end="12"/>
			<lne id="105" begin="13" end="13"/>
			<lne id="106" begin="11" end="14"/>
			<lne id="107" begin="9" end="16"/>
			<lne id="108" begin="19" end="19"/>
			<lne id="109" begin="17" end="21"/>
			<lne id="110" begin="24" end="24"/>
			<lne id="111" begin="24" end="25"/>
			<lne id="112" begin="22" end="27"/>
			<lne id="96" begin="8" end="28"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="3" name="94" begin="7" end="28"/>
			<lve slot="2" name="93" begin="3" end="28"/>
			<lve slot="0" name="17" begin="0" end="28"/>
			<lve slot="1" name="90" begin="0" end="28"/>
		</localvariabletable>
	</operation>
</asm>
