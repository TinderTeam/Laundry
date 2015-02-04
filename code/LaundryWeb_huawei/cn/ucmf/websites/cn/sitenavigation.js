/////////////////////////////////////////////////////////////////////////////
// Function : NavNode (constructor)
// Comments :
/////////////////////////////////////////////////////////////////////////////
function NavNode(id, label, href, parent)
{
	this.m_parent = null;
	this.m_level = 0;

	if (parent)
	{
		this.m_parent = parent;
		this.m_level = parent.m_level+1;
	}

	this.m_id = id;

	// assume that m_label will most often be used directly as HTML
	this.m_rawlabel = label;

	label = label.replace(/&/g, '&amp;');
	label = label.replace(/</g, '&lt;');
	label = label.replace(/>/g, '&gt;');
	label = label.replace(/"/g, '&quot;');

	this.m_label = label;

	this.m_href = href;
	this.m_subNodes = new Array();

	var argValues = NavNode.arguments;
	var argCount = NavNode.arguments.length;

	for (i = 4 ; i < argCount ; i++)
	{
		var eqPos = argValues[i].indexOf("==");
		var attrName = argValues[i].substring(0,eqPos);
		var attrValue = argValues[i].substring(eqPos+2);

		eval("this.cp_" + attrName + " = '" + attrValue + "';");
	}

	NavNode.prototype.addNode = addNode;
	NavNode.prototype.isSelected = isSelected;
}

/////////////////////////////////////////////////////////////////////////////
// Function : addNode
// Comments :
/////////////////////////////////////////////////////////////////////////////
function addNode(id, label, href)
{
	var newIndex = this.m_subNodes.length;
	var newNode = new NavNode(id, label, href, this);

	var argValues = addNode.arguments;
	var argCount = addNode.arguments.length;

	for (i = 3 ; i < argCount ; i++)
	{
		var eqPos = argValues[i].indexOf("==");
		var attrName = argValues[i].substring(0,eqPos);
		var attrValue = argValues[i].substring(eqPos+2);

		eval("newNode.cp_" + attrName + " = '" + attrValue + "';");
	}

	this.m_subNodes[newIndex] = newNode;
	return newNode;
}

/////////////////////////////////////////////////////////////////////////////
// Function : isSelected
// Comments :
/////////////////////////////////////////////////////////////////////////////
function isSelected()
{
    var pos = window.location.href.lastIndexOf("/");
    var docname = window.location.href.substring(pos+1, window.location.href.length);

    pos = this.m_href.lastIndexOf("/");
    var myname = this.m_href.substring(pos+1, this.m_href.length);

    if (docname == myname)
		return true;
	else
		return false;
}

/////////////////////////////////////////////////////////////////////////////
// Function : customSectionPropertyExists
// Comments :
/////////////////////////////////////////////////////////////////////////////
function customSectionPropertyExists(csp)
{
	return (typeof csp != _U && csp != null);
}

/////////////////////////////////////////////////////////////////////////////
// Function : getCustomSectionProperty
// Comments :
/////////////////////////////////////////////////////////////////////////////
function getCustomSectionProperty(csp)
{
	if (customSectionPropertyExists(csp))
	{
		return csp;
	}
	else
	{
		return "";
	}
}

/////////////////////////////////////////////////////////////////////////////

var g_navNode_Root = new NavNode('1','Worldwide',ssUrlPrefix + 'index.htm',null);
g_navNode_2=g_navNode_Root.addNode('2351','\u884c\u4e1a\u6d1e\u5bdf',ssUrlPrefix + 'industry/index.htm','isDynamic==FALSE');
g_navNode_2_0=g_navNode_2.addNode('2355','\u5ba2\u6237\u58f0\u97f3',ssUrlPrefix + 'industry/customer-voices/index.htm','secondaryUrlVariableField==creative_detail');
g_navNode_2_1=g_navNode_2.addNode('2354','\u6d88\u8d39\u8005\u7814\u7a76',ssUrlPrefix + 'industry/consumer-lab/index.htm','includeInContent==FALSE');
g_navNode_2_2=g_navNode_2.addNode('2357','\u521b\u65b0',ssUrlPrefix + 'industry/moving-forward/index.htm','includeInContent==TRUE','secondaryUrlVariableField==creative_detail');
g_navNode_2_3=g_navNode_2.addNode('2359','\u70ed\u70b9\u6280\u672f',ssUrlPrefix + 'industry/huawei-voices/index.htm','secondaryUrlVariableField==creative_detail');
g_navNode_2_4=g_navNode_2.addNode('2358','\u6807\u51c6\u4e0e\u884c\u4e1a\u8d21\u732e',ssUrlPrefix + 'industry/standards-contributions/index.htm','includeInContent==TRUE','secondaryUrlVariableField==creative_detail');
g_navNode_4=g_navNode_Root.addNode('2391','carrier',ssUrlPrefix + 'carrier/index.htm');
g_navNode_6=g_navNode_Root.addNode('2','\u89e3\u51b3\u65b9\u6848',ssUrlPrefix + 'solutions/index.htm','contributorOnly==FALSE');
g_navNode_6_0=g_navNode_6.addNode('3','\u589e\u52a0\u6536\u76ca',ssUrlPrefix + 'solutions/arpu-up/index.htm','secondaryUrlVariableField==article');
g_navNode_6_1=g_navNode_6.addNode('4','\u66f4\u5bbd\u66f4\u667a\u80fd',ssUrlPrefix + 'solutions/broader-smarter/index.htm','secondaryUrlVariableField==article');
g_navNode_6_2=g_navNode_6.addNode('5','\u63d0\u5347\u8fd0\u8425\u6548\u7387',ssUrlPrefix + 'solutions/costs-down/index.htm','secondaryUrlVariableField==article');
g_navNode_6_3=g_navNode_6.addNode('6','\u8282\u80fd\u51cf\u6392',ssUrlPrefix + 'solutions/go-greener/index.htm','secondaryUrlVariableField==article');
g_navNode_6_4=g_navNode_6.addNode('10891','\u56fa\u5b9a\u5bbd\u5e26',ssUrlPrefix + 'solutions/fbb/index.htm');
g_navNode_6_5=g_navNode_6.addNode('10895','\u5546\u4e1a\u7406\u5ff5',ssUrlPrefix + 'solutions/business/index.htm','secondaryUrlVariableField==article');
g_navNode_6_6=g_navNode_6.addNode('10896','\u4e1a\u754c\u6d1e\u5bdf',ssUrlPrefix + 'solutions/insights/index.htm','secondaryUrlVariableField==article');
g_navNode_6_7=g_navNode_6.addNode('10897','SoftCOM',ssUrlPrefix + 'solutions/softcom/index.htm');
g_navNode_6_8=g_navNode_6.addNode('11285','MBB\u200b',ssUrlPrefix + 'solutions/mbb/index.htm');
g_navNode_6_9=g_navNode_6.addNode('11286','Telco IT\u200b',ssUrlPrefix + 'solutions/telco-it/index.htm');
g_navNode_6_10=g_navNode_6.addNode('11290','\u5ba2\u6237\u4f53\u9a8c\u7ba1\u7406',ssUrlPrefix + 'solutions/cem/index.htm');
g_navNode_7=g_navNode_Root.addNode('7','\u4ea7\u54c1',ssUrlPrefix + 'products/index.htm');
g_navNode_7_0=g_navNode_7.addNode('10','\u65e0\u7ebf\u63a5\u5165',ssUrlPrefix + 'products/radio-access/index.htm');
g_navNode_7_0_0=g_navNode_7_0.addNode('59','\u591a\u6a21\u57fa\u7ad9',ssUrlPrefix + 'products/radio-access/singlebts/index.htm');
g_navNode_7_0_1=g_navNode_7_0.addNode('29','\u591a\u6a21\u57fa\u7ad9\u63a7\u5236\u5668',ssUrlPrefix + 'products/radio-access/multi-mode-bsc/index.htm');
g_navNode_7_0_2=g_navNode_7_0.addNode('12424','AAU',ssUrlPrefix + 'products/radio-access/aau/index.htm');
g_navNode_7_0_3=g_navNode_7_0.addNode('12425','\u4f17\u5305\u5c0f\u8702\u7a9d',ssUrlPrefix + 'products/radio-access/smallcell/index.htm');
g_navNode_7_0_4=g_navNode_7_0.addNode('12411','\u7ad9\u70b9',ssUrlPrefix + 'products/radio-access/SingleSite/index.htm');
g_navNode_7_1=g_navNode_7.addNode('11','\u56fa\u5b9a\u63a5\u5165',ssUrlPrefix + 'products/fixed-access/index.htm');
g_navNode_7_1_0=g_navNode_7_1.addNode('295','FTTx',ssUrlPrefix + 'products/fixed-access/fttx/index.htm');
g_navNode_7_1_0_0=g_navNode_7_1_0.addNode('66','OLT',ssUrlPrefix + 'products/fixed-access/fttx/olt/index.htm','externalUrl==/cn/products/fixed-access/fttx/olt/ma5680t/index.htm');
g_navNode_7_1_0_0_0=g_navNode_7_1_0_0.addNode('516','MA5680T',ssUrlPrefix + 'products/fixed-access/fttx/olt/ma5680t/index.htm');
g_navNode_7_1_0_1=g_navNode_7_1_0.addNode('67','MxU',ssUrlPrefix + 'products/fixed-access/fttx/mxu/index.htm');
g_navNode_7_1_0_1_0=g_navNode_7_1_0_1.addNode('8999','MA5611S',ssUrlPrefix + 'products/fixed-access/fttx/mxu/smartax-ma5611s/index.htm');
g_navNode_7_1_0_1_1=g_navNode_7_1_0_1.addNode('517','MA5612',ssUrlPrefix + 'products/fixed-access/fttx/mxu/ma5612/index.htm');
g_navNode_7_1_0_1_2=g_navNode_7_1_0_1.addNode('518','MA5616',ssUrlPrefix + 'products/fixed-access/fttx/mxu/ma5616/index.htm');
g_navNode_7_1_0_1_3=g_navNode_7_1_0_1.addNode('519','MA5620',ssUrlPrefix + 'products/fixed-access/fttx/mxu/ma5620/index.htm');
g_navNode_7_1_0_1_4=g_navNode_7_1_0_1.addNode('9048','MA5622A/23/23A',ssUrlPrefix + 'products/fixed-access/fttx/mxu/smartax-ma5622a/index.htm');
g_navNode_7_1_0_1_5=g_navNode_7_1_0_1.addNode('520','MA5626',ssUrlPrefix + 'products/fixed-access/fttx/mxu/ma5626/index.htm');
g_navNode_7_1_0_1_6=g_navNode_7_1_0_1.addNode('8997','MA5631/MA5632',ssUrlPrefix + 'products/fixed-access/fttx/mxu/smartax-ma5631/index.htm');
g_navNode_7_1_0_1_7=g_navNode_7_1_0_1.addNode('8998','MA5633',ssUrlPrefix + 'products/fixed-access/fttx/mxu/smartax-ma5633/index.htm');
g_navNode_7_1_0_1_8=g_navNode_7_1_0_1.addNode('8984','MA5650',ssUrlPrefix + 'products/fixed-access/fttx/mxu/smartax-ma5658/index.htm');
g_navNode_7_1_0_1_9=g_navNode_7_1_0_1.addNode('8991','MA5670',ssUrlPrefix + 'products/fixed-access/fttx/mxu/smartax-ma567x/index.htm');
g_navNode_7_1_0_1_10=g_navNode_7_1_0_1.addNode('8992','MA5690',ssUrlPrefix + 'products/fixed-access/fttx/mxu/smartax-ma569x/index.htm');
g_navNode_7_1_0_1_11=g_navNode_7_1_0_1.addNode('9081','\u6b63\u5411POE',ssUrlPrefix + 'products/fixed-access/fttx/mxu/poe-mxu/index.htm');
g_navNode_7_1_0_1_12=g_navNode_7_1_0_1.addNode('9082','\u53cd\u5411POE',ssUrlPrefix + 'products/fixed-access/fttx/mxu/poe/index.htm');
g_navNode_7_1_0_2=g_navNode_7_1_0.addNode('68','ONT',ssUrlPrefix + 'products/fixed-access/fttx/ont/index.htm');
g_navNode_7_1_0_2_0=g_navNode_7_1_0_2.addNode('521','HG813',ssUrlPrefix + 'products/fixed-access/fttx/ont/hg813/index.htm');
g_navNode_7_1_0_2_1=g_navNode_7_1_0_2.addNode('522','HG813e',ssUrlPrefix + 'products/fixed-access/fttx/ont/hg813e/index.htm');
g_navNode_7_1_0_2_2=g_navNode_7_1_0_2.addNode('523','HG8010',ssUrlPrefix + 'products/fixed-access/fttx/ont/hg8010/index.htm');
g_navNode_7_1_0_2_3=g_navNode_7_1_0_2.addNode('524','HG8110',ssUrlPrefix + 'products/fixed-access/fttx/ont/hg8110/index.htm');
g_navNode_7_1_0_2_4=g_navNode_7_1_0_2.addNode('525','HG8120',ssUrlPrefix + 'products/fixed-access/fttx/ont/hg8120/index.htm');
g_navNode_7_1_0_2_5=g_navNode_7_1_0_2.addNode('526','HG8120R',ssUrlPrefix + 'products/fixed-access/fttx/ont/hg8120r/index.htm');
g_navNode_7_1_0_2_6=g_navNode_7_1_0_2.addNode('527','HG8240',ssUrlPrefix + 'products/fixed-access/fttx/ont/hg8240/index.htm');
g_navNode_7_1_0_2_7=g_navNode_7_1_0_2.addNode('528','HG8240R',ssUrlPrefix + 'products/fixed-access/fttx/ont/hg8240r/index.htm');
g_navNode_7_1_0_2_8=g_navNode_7_1_0_2.addNode('529','HG8245',ssUrlPrefix + 'products/fixed-access/fttx/ont/hg8245/index.htm');
g_navNode_7_1_0_2_9=g_navNode_7_1_0_2.addNode('530','HG8247',ssUrlPrefix + 'products/fixed-access/fttx/ont/hg8247/index.htm');
g_navNode_7_1_0_3=g_navNode_7_1_0.addNode('11051','BITS',ssUrlPrefix + 'products/fixed-access/fttx/bits/index.htm');
g_navNode_7_1_1=g_navNode_7_1.addNode('69','\u5149\u7ea4\u57fa\u7840\u8bbe\u65bd',ssUrlPrefix + 'products/fixed-access/odn/index.htm');
g_navNode_7_1_1_0=g_navNode_7_1_1.addNode('531','\u5149\u7ea4\u914d\u7ebf\u67b6',ssUrlPrefix + 'products/fixed-access/odn/fddi/index.htm');
g_navNode_7_1_1_1=g_navNode_7_1_1.addNode('532','\u5149\u7f06\u4ea4\u63a5\u7bb1',ssUrlPrefix + 'products/fixed-access/odn/brilliance/index.htm');
g_navNode_7_1_1_1_0=g_navNode_7_1_1_1.addNode('6317','GXF147-iFDT3103D',ssUrlPrefix + 'products/fixed-access/odn/brilliance/GXF147-iFDT3103D/index.htm');
g_navNode_7_1_1_1_1=g_navNode_7_1_1_1.addNode('6318','GXF147-iFDT3101D-A',ssUrlPrefix + 'products/fixed-access/odn/brilliance/GXF147-iFDT3101D-A/index.htm');
g_navNode_7_1_1_2=g_navNode_7_1_1.addNode('533','\u5149\u5206\u8def\u5355\u5143',ssUrlPrefix + 'products/fixed-access/odn/photodynamics/index.htm');
g_navNode_7_1_1_3=g_navNode_7_1_1.addNode('534','\u5149\u7f06\u63a5\u5934\u76d2',ssUrlPrefix + 'products/fixed-access/odn/guanglan/index.htm');
g_navNode_7_1_1_4=g_navNode_7_1_1.addNode('535','\u5149\u7f06\u5206\u7ea4\u7bb1',ssUrlPrefix + 'products/fixed-access/odn/fenxian/index.htm');
g_navNode_7_1_1_4_0=g_navNode_7_1_1_4.addNode('6328','GPX147-iFAT3105T-32/36',ssUrlPrefix + 'products/fixed-access/odn/fenxian/GPX147-iFAT3105T/index.htm');
g_navNode_7_1_1_4_1=g_navNode_7_1_1_4.addNode('6326','GPX147-iFAT3103TD-F/P',ssUrlPrefix + 'products/fixed-access/odn/fenxian/GPX147-iFAT3103TD/index.htm');
g_navNode_7_1_1_4_2=g_navNode_7_1_1_4.addNode('6324','GPX147-iFAT3103T-16/24',ssUrlPrefix + 'products/fixed-access/odn/fenxian/GPX147-iFAT3103T/index.htm');
g_navNode_7_1_1_4_3=g_navNode_7_1_1_4.addNode('6322','GPX147-iFAT3102-24',ssUrlPrefix + 'products/fixed-access/odn/fenxian/GPX147-iFAT3102-24/index.htm');
g_navNode_7_1_1_4_4=g_navNode_7_1_1_4.addNode('6320','GPX147-iFAT3101TD-32/36',ssUrlPrefix + 'products/fixed-access/odn/fenxian/GPX147-iFAT3101TD/index.htm');
g_navNode_7_1_1_4_5=g_navNode_7_1_1_4.addNode('6319','GPX147-iFAT3101-48',ssUrlPrefix + 'products/fixed-access/odn/fenxian/GPX147-iFAT3101-48/index.htm');
g_navNode_7_1_1_5=g_navNode_7_1_1.addNode('536','\u5149\u7ea4\u5165\u6237\u7ec8\u7aef\u76d2',ssUrlPrefix + 'products/fixed-access/odn/guangxianruhu/index.htm');
g_navNode_7_1_1_6=g_navNode_7_1_1.addNode('537','\u5f31\u7535\u7bb1',ssUrlPrefix + 'products/fixed-access/odn/ruodian/index.htm');
g_navNode_7_1_1_7=g_navNode_7_1_1.addNode('538','\u5149\u7f06',ssUrlPrefix + 'products/fixed-access/odn/guanglanxilie/index.htm');
g_navNode_7_1_2=g_navNode_7_1.addNode('64','DSLAM',ssUrlPrefix + 'products/fixed-access/dslam/index.htm','externalUrl==/cn/products/fixed-access/dslam/ma5600/index.htm');
g_navNode_7_1_2_0=g_navNode_7_1_2.addNode('511','MA5600',ssUrlPrefix + 'products/fixed-access/dslam/ma5600/index.htm');
g_navNode_7_1_3=g_navNode_7_1.addNode('508','\u7efc\u5408\u63a5\u5165',ssUrlPrefix + 'products/fixed-access/colligate-access/index.htm','externalUrl==/cn/products/fixed-access/colligate-access/ua5000/index.htm');
g_navNode_7_1_3_0=g_navNode_7_1_3.addNode('509','UA5000',ssUrlPrefix + 'products/fixed-access/colligate-access/ua5000/index.htm');
g_navNode_7_1_4=g_navNode_7_1.addNode('510','\u96c6\u6210\u7ad9\u70b9',ssUrlPrefix + 'products/fixed-access/integration-site/index.htm','externalUrl==/cn/products/fixed-access/integration-site/unisite/index.htm');
g_navNode_7_1_4_0=g_navNode_7_1_4.addNode('70','uniSite',ssUrlPrefix + 'products/fixed-access/integration-site/unisite/index.htm');
g_navNode_7_2=g_navNode_7.addNode('13','\u6838\u5fc3\u7f51',ssUrlPrefix + 'products/core-network/index.htm');
g_navNode_7_2_0=g_navNode_7_2.addNode('57','CS ',ssUrlPrefix + 'products/core-network/singlecore/index.htm');
g_navNode_7_2_0_2=g_navNode_7_2_0.addNode('636','\u56fa\u5b9a\u8f6f\u4ea4\u6362',ssUrlPrefix + 'products/core-network/singlecore/fixed-softswitch/index.htm');
g_navNode_7_2_0_6=g_navNode_7_2_0.addNode('12454','\u79fb\u52a8\u8f6f\u4ea4\u6362',ssUrlPrefix + 'products/core-network/singlecore/Mobile-softswitch/index.htm');
g_navNode_7_2_0_7=g_navNode_7_2_0.addNode('12455','\u5a92\u4f53\u7f51\u5173',ssUrlPrefix + 'products/core-network/singlecore/UMG-N/index.htm');
g_navNode_7_2_1=g_navNode_7_2.addNode('58','IMS',ssUrlPrefix + 'products/core-network/ims/index.htm');
g_navNode_7_2_1_0=g_navNode_7_2_1.addNode('641','IMS Core',ssUrlPrefix + 'products/core-network/ims/ims-core/index.htm');
g_navNode_7_2_1_1=g_navNode_7_2_1.addNode('120','IMS AS',ssUrlPrefix + 'products/core-network/ims/ims-as/index.htm');
g_navNode_7_2_1_1_0=g_navNode_7_2_1_1.addNode('12426','ATS9900',ssUrlPrefix + 'products/core-network/ims/ims-as/ATS9900/index.htm');
g_navNode_7_2_1_1_1=g_navNode_7_2_1_1.addNode('12427','Mediax3600',ssUrlPrefix + 'products/core-network/ims/ims-as/Mediax3600/index.htm');
g_navNode_7_2_1_1_2=g_navNode_7_2_1_1.addNode('12430','RCS9800',ssUrlPrefix + 'products/core-network/ims/ims-as/RCS9800/index.htm');
g_navNode_7_2_1_2=g_navNode_7_2_1.addNode('119','SBC',ssUrlPrefix + 'products/core-network/ims/sbc/index.htm');
g_navNode_7_2_1_3=g_navNode_7_2_1.addNode('646','SPS',ssUrlPrefix + 'products/core-network/ims/sps/index.htm');
g_navNode_7_2_2=g_navNode_7_2.addNode('642','\u878d\u5408\u7528\u6237\u6570\u636e',ssUrlPrefix + 'products/core-network/singlesdb/index.htm');
g_navNode_7_2_2_6=g_navNode_7_2_2.addNode('12456','UIM',ssUrlPrefix + 'products/core-network/singlesdb/3gpp-aaa/index.htm');
g_navNode_7_2_2_7=g_navNode_7_2_2.addNode('12457','HLR/HSS',ssUrlPrefix + 'products/core-network/singlesdb/hss/index.htm');
g_navNode_7_2_2_8=g_navNode_7_2_2.addNode('12458','PCRF',ssUrlPrefix + 'products/core-network/singlesdb/pcrf/index.htm');
g_navNode_7_2_2_9=g_navNode_7_2_2.addNode('12459','PolicyView',ssUrlPrefix + 'products/core-network/singlesdb/policyview/index.htm');
g_navNode_7_2_2_10=g_navNode_7_2_2.addNode('12460','USCDB',ssUrlPrefix + 'products/core-network/singlesdb/uscdb/index.htm');
g_navNode_7_3=g_navNode_7.addNode('93','\u4f20\u9001\u7f51',ssUrlPrefix + 'products/transport-network/index.htm');
g_navNode_7_3_0=g_navNode_7_3.addNode('97','WDM/OTN',ssUrlPrefix + 'products/transport-network/wdm-otn/index.htm');
g_navNode_7_3_0_0=g_navNode_7_3_0.addNode('12651','T-SDN  ',ssUrlPrefix + 'products/transport-network/wdm-otn/t-sdn/index.htm');
g_navNode_7_3_0_1=g_navNode_7_3_0.addNode('6658','OSN 9800',ssUrlPrefix + 'products/transport-network/wdm-otn/osn9800/index.htm');
g_navNode_7_3_0_2=g_navNode_7_3_0.addNode('11072','OSN 9600',ssUrlPrefix + 'products/transport-network/wdm-otn/osn9600/index.htm');
g_navNode_7_3_0_3=g_navNode_7_3_0.addNode('574','OSN 8800',ssUrlPrefix + 'products/transport-network/wdm-otn/osn8800/index.htm');
g_navNode_7_3_0_4=g_navNode_7_3_0.addNode('575','OSN 6800\x263800',ssUrlPrefix + 'products/transport-network/wdm-otn/osn-68003800/index.htm');
g_navNode_7_3_0_5=g_navNode_7_3_0.addNode('576','OSN 1800',ssUrlPrefix + 'products/transport-network/wdm-otn/osn1800/index.htm');
g_navNode_7_3_0_6=g_navNode_7_3_0.addNode('573','BWS 1600G',ssUrlPrefix + 'products/transport-network/wdm-otn/bwS1600g/index.htm');
g_navNode_7_3_1=g_navNode_7_3.addNode('560','\u5fae\u6ce2',ssUrlPrefix + 'products/transport-network/weibo/index.htm');
g_navNode_7_3_1_0=g_navNode_7_3_1.addNode('12376','RTN 980L',ssUrlPrefix + 'products/transport-network/weibo/RTN980L/index.htm');
g_navNode_7_3_1_1=g_navNode_7_3_1.addNode('578','RTN 900',ssUrlPrefix + 'products/transport-network/weibo/rtn900/index.htm');
g_navNode_7_3_1_2=g_navNode_7_3_1.addNode('6631','RTN380',ssUrlPrefix + 'products/transport-network/weibo/rtn380/index.htm');
g_navNode_7_3_1_3=g_navNode_7_3_1.addNode('12377','RTN 360',ssUrlPrefix + 'products/transport-network/weibo/RTN360/index.htm');
g_navNode_7_3_1_4=g_navNode_7_3_1.addNode('12378','RTN 320',ssUrlPrefix + 'products/transport-network/weibo/RTN320/index.htm');
g_navNode_7_3_2=g_navNode_7_3.addNode('559','Hybrid MSTP',ssUrlPrefix + 'products/transport-network/hybrid-mstp/index.htm');
g_navNode_7_3_2_0=g_navNode_7_3_2.addNode('567','OSN 7500 II',ssUrlPrefix + 'products/transport-network/hybrid-mstp/osn7500ii/index.htm');
g_navNode_7_3_2_1=g_navNode_7_3_2.addNode('746','OSN 7500',ssUrlPrefix + 'products/transport-network/hybrid-mstp/osn7500/index.htm');
g_navNode_7_3_2_2=g_navNode_7_3_2.addNode('568','OSN 3500',ssUrlPrefix + 'products/transport-network/hybrid-mstp/osn3500/index.htm');
g_navNode_7_3_2_3=g_navNode_7_3_2.addNode('569','OSN 1500',ssUrlPrefix + 'products/transport-network/hybrid-mstp/osn1500/index.htm');
g_navNode_7_3_2_4=g_navNode_7_3_2.addNode('12373','OSN 580',ssUrlPrefix + 'products/transport-network/hybrid-mstp/OSN580/index.htm');
g_navNode_7_3_2_5=g_navNode_7_3_2.addNode('570','OSN 550',ssUrlPrefix + 'products/transport-network/hybrid-mstp/osn550/index.htm');
g_navNode_7_3_2_6=g_navNode_7_3_2.addNode('571','OSN 500',ssUrlPrefix + 'products/transport-network/hybrid-mstp/osn500/index.htm');
g_navNode_7_3_3=g_navNode_7_3.addNode('558','MSTP',ssUrlPrefix + 'products/transport-network/MSTP/index.htm');
g_navNode_7_3_3_0=g_navNode_7_3_3.addNode('562','OSN 9560',ssUrlPrefix + 'products/transport-network/MSTP/osn9560/index.htm');
g_navNode_7_3_3_1=g_navNode_7_3_3.addNode('563','OSN 9500',ssUrlPrefix + 'products/transport-network/MSTP/osn9500/index.htm');
g_navNode_7_3_3_2=g_navNode_7_3_3.addNode('564','OSN 3500 II',ssUrlPrefix + 'products/transport-network/MSTP/osn3500ii/index.htm');
g_navNode_7_3_3_3=g_navNode_7_3_3.addNode('565','OSN 2500',ssUrlPrefix + 'products/transport-network/MSTP/osn2500/index.htm');
g_navNode_7_3_3_4=g_navNode_7_3_3.addNode('12374','Metro 1000',ssUrlPrefix + 'products/transport-network/MSTP/Metro1000/index.htm');
g_navNode_7_3_3_5=g_navNode_7_3_3.addNode('566','Metro 100',ssUrlPrefix + 'products/transport-network/MSTP/metro100/index.htm');
g_navNode_7_3_4=g_navNode_7_3.addNode('561','\u6d77\u7f06\u901a\u4fe1',ssUrlPrefix + 'products/transport-network/hailantonxin/index.htm','externalUrl==http\x3a//www.huaweimarine.com/marine/');
g_navNode_7_4=g_navNode_7.addNode('12','\u8def\u7531\u5668\u4e0e\u7535\u4fe1\u4ee5\u592a',ssUrlPrefix + 'products/data-communication/index.htm');
g_navNode_7_4_0=g_navNode_7_4.addNode('12461','\u63a7\u5236\u5668',ssUrlPrefix + 'products/data-communication/SNC/index.htm');
g_navNode_7_4_1=g_navNode_7_4.addNode('12462','\u6838\u5fc3\u8def\u7531\u5668',ssUrlPrefix + 'products/data-communication/NE5000E/index.htm');
g_navNode_7_4_2=g_navNode_7_4.addNode('12463','\u4e1a\u52a1\u8def\u7531\u5668',ssUrlPrefix + 'products/data-communication/NE40E/index.htm');
g_navNode_7_4_2_0=g_navNode_7_4_2.addNode('12506','NE40E',ssUrlPrefix + 'products/data-communication/NE40E/NE40E/index.htm');
g_navNode_7_4_2_1=g_navNode_7_4_2.addNode('12621','CX600',ssUrlPrefix + 'products/data-communication/NE40E/CX600/index.htm');
g_navNode_7_4_3=g_navNode_7_4.addNode('12464','\u591a\u4e1a\u52a1\u63a5\u5165\u8def\u7531\u5668',ssUrlPrefix + 'products/data-communication/Multi-service/index.htm');
g_navNode_7_4_3_0=g_navNode_7_4_3.addNode('12624','ATN950B',ssUrlPrefix + 'products/data-communication/Multi-service/ATN950B/index.htm');
g_navNode_7_4_3_1=g_navNode_7_4_3.addNode('12627','ATN910',ssUrlPrefix + 'products/data-communication/Multi-service/ATN910/index.htm');
g_navNode_7_4_3_2=g_navNode_7_4_3.addNode('12628','ATN905',ssUrlPrefix + 'products/data-communication/Multi-service/ATN905/index.htm');
g_navNode_7_4_4=g_navNode_7_4.addNode('12465','\u539f\u5b50\u8def\u7531\u5668',ssUrlPrefix + 'products/data-communication/AtomEngine/index.htm');
g_navNode_7_4_5=g_navNode_7_4.addNode('86','PTN',ssUrlPrefix + 'products/data-communication/ptn/index.htm');
g_navNode_7_4_5_0=g_navNode_7_4_5.addNode('12629','PTN7900',ssUrlPrefix + 'products/data-communication/ptn/PTN7900/index.htm');
g_navNode_7_4_5_1=g_navNode_7_4_5.addNode('12630','PTN6900',ssUrlPrefix + 'products/data-communication/ptn/PTN6900/index.htm');
g_navNode_7_4_5_2=g_navNode_7_4_5.addNode('12637','PTN3900',ssUrlPrefix + 'products/data-communication/ptn/PTN3900/index.htm');
g_navNode_7_4_5_3=g_navNode_7_4_5.addNode('12638','PTN1900',ssUrlPrefix + 'products/data-communication/ptn/PTN1900/index.htm');
g_navNode_7_4_5_4=g_navNode_7_4_5.addNode('12639','PTN900',ssUrlPrefix + 'products/data-communication/ptn/PTN900/index.htm');
g_navNode_7_4_6=g_navNode_7_4.addNode('12820','\u6570\u636e\u4e2d\u5fc3\u4ea4\u6362\u673a ',ssUrlPrefix + 'products/data-communication/data-center-switches/index.htm');
g_navNode_7_4_7=g_navNode_7_4.addNode('87','\u4ee5\u592a\u4ea4\u6362\u673a',ssUrlPrefix + 'products/data-communication/ethernet-switches/index.htm');
g_navNode_7_4_7_0=g_navNode_7_4_7.addNode('11416','S12700',ssUrlPrefix + 'products/data-communication/ethernet-switches/s12700/index.htm');
g_navNode_7_4_7_1=g_navNode_7_4_7.addNode('7035','S9300E',ssUrlPrefix + 'products/data-communication/ethernet-switches/s9300E/index.htm');
g_navNode_7_4_7_2=g_navNode_7_4_7.addNode('751','S9300',ssUrlPrefix + 'products/data-communication/ethernet-switches/s9300/index.htm');
g_navNode_7_4_7_3=g_navNode_7_4_7.addNode('551','S6300',ssUrlPrefix + 'products/data-communication/ethernet-switches/S6300/index.htm');
g_navNode_7_4_7_4=g_navNode_7_4_7.addNode('748','S5300',ssUrlPrefix + 'products/data-communication/ethernet-switches/s5300/index.htm');
g_navNode_7_4_7_5=g_navNode_7_4_7.addNode('12069','S5320EI',ssUrlPrefix + 'products/data-communication/ethernet-switches/s5320ei/index.htm');
g_navNode_7_4_7_6=g_navNode_7_4_7.addNode('749','S3300',ssUrlPrefix + 'products/data-communication/ethernet-switches/s3300/index.htm');
g_navNode_7_4_7_7=g_navNode_7_4_7.addNode('750','S2300',ssUrlPrefix + 'products/data-communication/ethernet-switches/s2300/index.htm');
g_navNode_7_4_8=g_navNode_7_4.addNode('84','\u5b89\u5168\u4ea7\u54c1',ssUrlPrefix + 'products/data-communication/security/index.htm');
g_navNode_7_4_8_0=g_navNode_7_4_8.addNode('640','\u9632\u706b\u5899\x26VPN',ssUrlPrefix + 'products/data-communication/security/Firewall/index.htm');
g_navNode_7_4_8_0_0=g_navNode_7_4_8_0.addNode('905','T\u7ea7\u9632\u706b\u5899Eudemon8000E-X\u7cfb\u5217',ssUrlPrefix + 'products/data-communication/security/Firewall/E8000E/index.htm');
g_navNode_7_4_8_0_1=g_navNode_7_4_8_0.addNode('11147','Eudemon200E-N/1000E-N',ssUrlPrefix + 'products/data-communication/security/Firewall/eudemon200e-n-1000e-n/index.htm');
g_navNode_7_4_8_0_2=g_navNode_7_4_8_0.addNode('799','E1000E-X \u7cfb\u5217',ssUrlPrefix + 'products/data-communication/security/Firewall/E1000E-X/index.htm');
g_navNode_7_4_8_0_3=g_navNode_7_4_8_0.addNode('10594','E200E \u7cfb\u5217',ssUrlPrefix + 'products/data-communication/security/Firewall/e200e/index.htm');
g_navNode_7_4_8_0_4=g_navNode_7_4_8_0.addNode('10595','SVN2000\x265000',ssUrlPrefix + 'products/data-communication/security/Firewall/svn20005000/index.htm');
g_navNode_7_4_8_0_5=g_navNode_7_4_8_0.addNode('904','Huawei eLog',ssUrlPrefix + 'products/data-communication/security/Firewall/eLog/index.htm');
g_navNode_7_4_8_1=g_navNode_7_4_8.addNode('710','\u5f02\u5e38\u6d41\u91cf\u7ba1\u7406',ssUrlPrefix + 'products/data-communication/security/abnormity/index.htm');
g_navNode_7_4_8_1_0=g_navNode_7_4_8_1.addNode('896','AntiDDoS8000\u7cfb\u5217',ssUrlPrefix + 'products/data-communication/security/abnormity/Anti-DDoS/index.htm');
g_navNode_7_4_8_1_1=g_navNode_7_4_8_1.addNode('895','AntiDDoS1000\u7cfb\u5217',ssUrlPrefix + 'products/data-communication/security/abnormity/E1000E-ID/index.htm');
g_navNode_7_4_8_2=g_navNode_7_4_8.addNode('708','\u5165\u4fb5\u68c0\u6d4b\u4e0e\u9632\u5fa1\u7cfb\u7edf',ssUrlPrefix + 'products/data-communication/security/content-security/index.htm');
g_navNode_7_4_8_2_0=g_navNode_7_4_8_2.addNode('10596','NIP2000D/5000D \u7cfb\u5217',ssUrlPrefix + 'products/data-communication/security/content-security/nip2000d_5000d/index.htm');
g_navNode_7_4_8_2_1=g_navNode_7_4_8_2.addNode('812','NIP2000/5000 \u7cfb\u5217',ssUrlPrefix + 'products/data-communication/security/content-security/NIP/index.htm');
g_navNode_7_4_8_3=g_navNode_7_4_8.addNode('711','\u7f51\u7edc\u7f13\u5b58',ssUrlPrefix + 'products/data-communication/security/network/index.htm');
g_navNode_7_4_8_3_0=g_navNode_7_4_8_3.addNode('897','Internet Cache\u4e92\u8054\u7f51\u7f13\u5b58\u7cfb\u7edf',ssUrlPrefix + 'products/data-communication/security/network/internet-cache/index.htm');
g_navNode_7_4_8_4=g_navNode_7_4_8.addNode('747','\u7ec8\u7aef\u5b89\u5168',ssUrlPrefix + 'products/data-communication/security/terminal/index.htm');
g_navNode_7_4_8_4_0=g_navNode_7_4_8_4.addNode('902','TSM \u7ec8\u7aef\u5b89\u5168\u7ba1\u7406\u7cfb\u7edf',ssUrlPrefix + 'products/data-communication/security/terminal/TSM/index.htm');
g_navNode_7_4_8_4_1=g_navNode_7_4_8_4.addNode('903','DSM \u7ec8\u7aef\u5b89\u5168\u7ba1\u7406\u7cfb\u7edf',ssUrlPrefix + 'products/data-communication/security/terminal/DSM/index.htm');
g_navNode_7_4_8_5=g_navNode_7_4_8.addNode('709','\u6d41\u91cf\u7ecf\u8425',ssUrlPrefix + 'products/data-communication/security/sig/index.htm');
g_navNode_7_4_8_5_0=g_navNode_7_4_8_5.addNode('893','SIG9800-X\u7cfb\u5217\u4e1a\u52a1\u76d1\u63a7\u7f51\u5173',ssUrlPrefix + 'products/data-communication/security/sig/SIG9800/index.htm');
g_navNode_7_4_8_5_1=g_navNode_7_4_8_5.addNode('894','SIG9280E\u4e1a\u52a1\u667a\u80fd\u7f51\u5173',ssUrlPrefix + 'products/data-communication/security/sig/SIG9280E/index.htm');
g_navNode_7_4_8_6=g_navNode_7_4_8.addNode('712','\u5408\u89c4\u7c7b',ssUrlPrefix + 'products/data-communication/security/normally/index.htm');
g_navNode_7_4_8_6_0=g_navNode_7_4_8_6.addNode('899','iSoc \u7edf\u4e00\u5b89\u5168\u7ba1\u63a7\u4e2d\u5fc3',ssUrlPrefix + 'products/data-communication/security/normally/iSoc/index.htm');
g_navNode_7_4_9=g_navNode_7_4.addNode('12472','\u591a\u4e1a\u52a1\u63a7\u5236\u7f51\u5173',ssUrlPrefix + 'products/data-communication/ME60/index.htm');
g_navNode_7_4_10=g_navNode_7_4.addNode('12487','SIG\u4e1a\u52a1\u667a\u80fd\u7f51\u5173',ssUrlPrefix + 'products/data-communication/SIG9800/index.htm');
g_navNode_7_5=g_navNode_7.addNode('696','\u7f51\u7edc\u80fd\u6e90',ssUrlPrefix + 'products/network-energy/index.htm');
g_navNode_7_5_0=g_navNode_7_5.addNode('5286','\u7ad9\u70b9\u7535\u6e90',ssUrlPrefix + 'products/network-energy/site-power/index.htm');
g_navNode_7_5_0_0=g_navNode_7_5_0.addNode('6652','\u5d4c\u5165\u5f0f\u7535\u6e90',ssUrlPrefix + 'products/network-energy/site-power/embedded/index.htm');
g_navNode_7_5_0_1=g_navNode_7_5_0.addNode('6653','\u5ba4\u5185\u7535\u6e90',ssUrlPrefix + 'products/network-energy/site-power/indoor/index.htm');
g_navNode_7_5_0_2=g_navNode_7_5_0.addNode('6654','\u5ba4\u5916\u7535\u6e90',ssUrlPrefix + 'products/network-energy/site-power/outdoor/index.htm');
g_navNode_7_5_0_3=g_navNode_7_5_0.addNode('700','Mini\u673a\u623f',ssUrlPrefix + 'products/network-energy/site-power/Mini-shelter/index.htm');
g_navNode_7_5_0_8=g_navNode_7_5_0.addNode('10218','\u4e2d\u5fc3\u673a\u623f\u5927\u7535\u6e90',ssUrlPrefix + 'products/network-energy/site-power/center-room/index.htm');
g_navNode_7_5_0_9=g_navNode_7_5_0.addNode('10226','\u58c1\u6302\u7535\u6e90',ssUrlPrefix + 'products/network-energy/site-power/Wall-mounted/index.htm');
g_navNode_7_5_1=g_navNode_7_5.addNode('5288','\u6570\u636e\u4e2d\u5fc3\u57fa\u7840\u8bbe\u65bd',ssUrlPrefix + 'products/network-energy/Data-Center-Energy/index.htm');
g_navNode_7_5_1_0=g_navNode_7_5_1.addNode('10269','\u4e00\u4f53\u5316\u96c6\u88c5\u7bb1\u6570\u636e\u4e2d\u5fc3',ssUrlPrefix + 'products/network-energy/Data-Center-Energy/IDS1000-A/index.htm');
g_navNode_7_5_1_1=g_navNode_7_5_1.addNode('10452','\u96c6\u7fa4\u5f0f\u96c6\u88c5\u7bb1\u6570\u636e\u4e2d\u5fc3',ssUrlPrefix + 'products/network-energy/Data-Center-Energy/IDS1000-C/index.htm');
g_navNode_7_5_1_2=g_navNode_7_5_1.addNode('10456','\u5c0f\u578b\u6a21\u5757\u5316\u6570\u636e\u4e2d\u5fc3',ssUrlPrefix + 'products/network-energy/Data-Center-Energy/IDS2000-S/index.htm');
g_navNode_7_5_1_3=g_navNode_7_5_1.addNode('10457','\u4e2d\u578b\u6a21\u5757\u5316\u6570\u636e\u4e2d\u5fc3',ssUrlPrefix + 'products/network-energy/Data-Center-Energy/IDS2000-M/index.htm');
g_navNode_7_5_1_4=g_navNode_7_5_1.addNode('10458','\u5927\u578b\u6a21\u5757\u5316\u636e\u4e2d\u5fc3',ssUrlPrefix + 'products/network-energy/Data-Center-Energy/IDS2000-L/index.htm');
g_navNode_7_5_2=g_navNode_7_5.addNode('159','\u6df7\u5408\u4f9b\u7535',ssUrlPrefix + 'products/network-energy/hybrid-power/index.htm');
g_navNode_7_5_2_0=g_navNode_7_5_2.addNode('10260','\u6cb9\u6df7\u89e3\u51b3\u65b9\u6848',ssUrlPrefix + 'products/network-energy/hybrid-power/Diesel-hybrid/index.htm');
g_navNode_7_5_2_1=g_navNode_7_5_2.addNode('10266','\u5149\u6df7\u89e3\u51b3\u65b9\u6848',ssUrlPrefix + 'products/network-energy/hybrid-power/Solar-hybrid/index.htm');
g_navNode_7_5_2_2=g_navNode_7_5_2.addNode('10267','\u7535\u6df7\u89e3\u51b3\u65b9\u6848',ssUrlPrefix + 'products/network-energy/hybrid-power/Grid-hybrid/index.htm');
g_navNode_7_5_3=g_navNode_7_5.addNode('9779','\u4e0d\u95f4\u65ad\u7535\u6e90',ssUrlPrefix + 'products/network-energy/ups/index.htm');
g_navNode_7_5_3_0=g_navNode_7_5_3.addNode('9783','UPS2000-G',ssUrlPrefix + 'products/network-energy/ups/Ups2000-g/index.htm');
g_navNode_7_5_3_1=g_navNode_7_5_3.addNode('9803','UPS5000-E ',ssUrlPrefix + 'products/network-energy/ups/ups5000-e/index.htm');
g_navNode_7_5_3_2=g_navNode_7_5_3.addNode('9922','UPS5000-A',ssUrlPrefix + 'products/network-energy/ups/Ups5000-a/index.htm');
g_navNode_7_5_3_3=g_navNode_7_5_3.addNode('10214','UPS8000-D',ssUrlPrefix + 'products/network-energy/ups/ups8000-d/index.htm');
g_navNode_7_6=g_navNode_7.addNode('14','\u7535\u4fe1\u8f6f\u4ef6',ssUrlPrefix + 'products/software/index.htm');
g_navNode_7_6_0=g_navNode_7_6.addNode('129','\u6570\u5b57\u4e1a\u52a1',ssUrlPrefix + 'products/software/consumer/index.htm');
g_navNode_7_6_0_0=g_navNode_7_6_0.addNode('6673','CDN\x26Cache',ssUrlPrefix + 'products/software/consumer/cdn-cache/index.htm');
g_navNode_7_6_0_0_0=g_navNode_7_6_0_0.addNode('6674','\u4e92\u8054\u7f51\u7f13\u5b58',ssUrlPrefix + 'products/software/consumer/cdn-cache/internet-cache/index.htm');
g_navNode_7_6_0_0_1=g_navNode_7_6_0_0.addNode('6675','SmartCDN',ssUrlPrefix + 'products/software/consumer/cdn-cache/smartcdn/index.htm');
g_navNode_7_6_0_1=g_navNode_7_6_0.addNode('135','SDP',ssUrlPrefix + 'products/software/consumer/sdp/index.htm');
g_navNode_7_6_0_1_0=g_navNode_7_6_0_1.addNode('774','\u4e1a\u52a1\u4ea4\u4ed8\u5e73\u53f0\uff08SDP\uff09',ssUrlPrefix + 'products/software/consumer/sdp/SDP/index.htm');
g_navNode_7_6_0_1_1=g_navNode_7_6_0_1.addNode('775','\u79fb\u52a8\u5bbd\u5e26\u4ef7\u503c\u589e\u957f\u89e3\u51b3\u65b9\u6848\uff08MBB VGS\uff09',ssUrlPrefix + 'products/software/consumer/sdp/mbbvgs/index.htm');
g_navNode_7_6_0_1_2=g_navNode_7_6_0_1.addNode('776','\u6570\u5b57\u5546\u57ce\uff08DSM\uff09',ssUrlPrefix + 'products/software/consumer/sdp/dsm/index.htm');
g_navNode_7_6_0_2=g_navNode_7_6_0.addNode('136','\u6570\u5b57\u5bb6\u5ead',ssUrlPrefix + 'products/software/consumer/digital-home/index.htm');
g_navNode_7_6_0_2_0=g_navNode_7_6_0_2.addNode('777','IPTV',ssUrlPrefix + 'products/software/consumer/digital-home/iptv/index.htm');
g_navNode_7_6_0_2_1=g_navNode_7_6_0_2.addNode('778','WiHome',ssUrlPrefix + 'products/software/consumer/digital-home/wihome/index.htm');
g_navNode_7_6_0_2_2=g_navNode_7_6_0_2.addNode('779','DTV',ssUrlPrefix + 'products/software/consumer/digital-home/dtv/index.htm');
g_navNode_7_6_0_3=g_navNode_7_6_0.addNode('137','\u878d\u5408\u901a\u4fe1',ssUrlPrefix + 'products/software/consumer/rcs/index.htm');
g_navNode_7_6_0_3_0=g_navNode_7_6_0_3.addNode('780','\u878d\u5408\u901a\u4fe1\uff08RCS\uff09',ssUrlPrefix + 'products/software/consumer/rcs/rcs/index.htm');
g_navNode_7_6_0_3_1=g_navNode_7_6_0_3.addNode('781','\u77ed\u6d88\u606f',ssUrlPrefix + 'products/software/consumer/rcs/sms/index.htm');
g_navNode_7_6_0_3_2=g_navNode_7_6_0_3.addNode('782','\u8bed\u97f3\u90ae\u7bb1',ssUrlPrefix + 'products/software/consumer/rcs/vms/index.htm');
g_navNode_7_6_0_3_3=g_navNode_7_6_0_3.addNode('783','\u591a\u5a92\u4f53\u6d88\u606f',ssUrlPrefix + 'products/software/consumer/rcs/mms/index.htm');
g_navNode_7_6_0_3_4=g_navNode_7_6_0_3.addNode('784','NGIN',ssUrlPrefix + 'products/software/consumer/rcs/ngin/index.htm');
g_navNode_7_6_1=g_navNode_7_6.addNode('128','\u8fd0\u8425\u652f\u6491',ssUrlPrefix + 'products/software/bss/index.htm');
g_navNode_7_6_1_0=g_navNode_7_6_1.addNode('131','NGBSS',ssUrlPrefix + 'products/software/bss/ngbss/index.htm');
g_navNode_7_6_1_0_0=g_navNode_7_6_1_0.addNode('756','NGBSS',ssUrlPrefix + 'products/software/bss/ngbss/ngbss/index.htm');
g_navNode_7_6_1_0_1=g_navNode_7_6_1_0.addNode('757','\u5408\u4f5c\u4f19\u4f34\u5173\u7cfb\u7ba1\u7406',ssUrlPrefix + 'products/software/bss/ngbss/prm/index.htm');
g_navNode_7_6_1_0_2=g_navNode_7_6_1_0.addNode('758','\u5546\u4e1a\u667a\u80fd',ssUrlPrefix + 'products/software/bss/ngbss/bi/index.htm');
g_navNode_7_6_1_0_3=g_navNode_7_6_1_0.addNode('759','\u667a\u80fd\u547c\u53eb\u4e2d\u5fc3',ssUrlPrefix + 'products/software/bss/ngbss/ipcc/index.htm');
g_navNode_7_6_1_1=g_navNode_7_6_1.addNode('132','NGCRM',ssUrlPrefix + 'products/software/bss/ngcrm/index.htm');
g_navNode_7_6_1_1_0=g_navNode_7_6_1_1.addNode('760','CRM',ssUrlPrefix + 'products/software/bss/ngcrm/crm/index.htm');
g_navNode_7_6_1_1_1=g_navNode_7_6_1_1.addNode('761','\u8425\u9500\u7ba1\u7406',ssUrlPrefix + 'products/software/bss/ngcrm/campaign-management/index.htm');
g_navNode_7_6_1_1_2=g_navNode_7_6_1_1.addNode('762','\u5ba2\u6237\u5173\u6000',ssUrlPrefix + 'products/software/bss/ngcrm/customer-care/index.htm');
g_navNode_7_6_1_1_3=g_navNode_7_6_1_1.addNode('763','\u9500\u552e\u7ba1\u7406',ssUrlPrefix + 'products/software/bss/ngcrm/sales-management/index.htm');
g_navNode_7_6_1_1_4=g_navNode_7_6_1_1.addNode('764','\u81ea\u52a9\u670d\u52a1',ssUrlPrefix + 'products/software/bss/ngcrm/selfcare/index.htm');
g_navNode_7_6_1_2=g_navNode_7_6_1.addNode('133','NGRM',ssUrlPrefix + 'products/software/bss/ngrm/index.htm');
g_navNode_7_6_1_2_0=g_navNode_7_6_1_2.addNode('765','NGRM',ssUrlPrefix + 'products/software/bss/ngrm/ngrm/index.htm');
g_navNode_7_6_1_2_1=g_navNode_7_6_1_2.addNode('766','\u878d\u5408\u8ba1\u8d39',ssUrlPrefix + 'products/software/bss/ngrm/cbs/index.htm');
g_navNode_7_6_1_2_2=g_navNode_7_6_1_2.addNode('767','\u6b3a\u8bc8\u7ba1\u7406\u7cfb\u7edf',ssUrlPrefix + 'products/software/bss/ngrm/fraud/index.htm');
g_navNode_7_6_1_2_3=g_navNode_7_6_1_2.addNode('768','Mediation\u7cfb\u7edf',ssUrlPrefix + 'products/software/bss/ngrm/mediation/index.htm');
g_navNode_7_6_1_2_4=g_navNode_7_6_1_2.addNode('769','\u79fb\u52a8\u652f\u4ed8',ssUrlPrefix + 'products/software/bss/ngrm/mobilemoney/index.htm');
g_navNode_7_6_1_3=g_navNode_7_6_1.addNode('134','\u7ba1\u7406\u670d\u52a1',ssUrlPrefix + 'products/software/bss/bss-oss/index.htm');
g_navNode_7_6_1_3_0=g_navNode_7_6_1_3.addNode('770','BSS/OSS\u7ba1\u7406\u670d\u52a1',ssUrlPrefix + 'products/software/bss/bss-oss/BSS-OSSmanagament/index.htm');
g_navNode_7_6_1_3_1=g_navNode_7_6_1_3.addNode('771','\u4e1a\u52a1\u6d41\u7a0b\u5916\u5305\u670d\u52a1',ssUrlPrefix + 'products/software/bss/bss-oss/bpo/index.htm');
g_navNode_7_6_1_3_2=g_navNode_7_6_1_3.addNode('772','IT\u7ba1\u7406\u670d\u52a1',ssUrlPrefix + 'products/software/bss/bss-oss/ito/index.htm');
g_navNode_7_6_1_3_3=g_navNode_7_6_1_3.addNode('773','\u8f6c\u578b\u670d\u52a1',ssUrlPrefix + 'products/software/bss/bss-oss/transformation/index.htm');
g_navNode_7_7=g_navNode_7.addNode('290','OSS',ssUrlPrefix + 'products/oss/index.htm');
g_navNode_7_7_0=g_navNode_7_7.addNode('11142','OSS \u4ea7\u54c1\u4e0e\u89e3\u51b3\u65b9\u6848',ssUrlPrefix + 'products/oss/mv-oss/index.htm');
g_navNode_7_7_1=g_navNode_7_7.addNode('580','FBB\u8fd0\u7ef4\u7cfb\u5217\u4ea7\u54c1',ssUrlPrefix + 'products/oss/fbb/index.htm');
g_navNode_7_7_1_0=g_navNode_7_7_1.addNode('6610','iManager uTraffic',ssUrlPrefix + 'products/oss/fbb/iManageruTraffic/index.htm');
g_navNode_7_7_1_1=g_navNode_7_7_1.addNode('588','iManager U2000',ssUrlPrefix + 'products/oss/fbb/imanageru2000/index.htm');
g_navNode_7_7_1_2=g_navNode_7_7_1.addNode('589','iManager N2510',ssUrlPrefix + 'products/oss/fbb/imanagern2510/index.htm');
g_navNode_7_7_1_3=g_navNode_7_7_1.addNode('10579','iManager U2520',ssUrlPrefix + 'products/oss/fbb/imanager-u2520/index.htm');
g_navNode_7_7_2=g_navNode_7_7.addNode('581','MBB\u8fd0\u7ef4\u7cfb\u5217\u4ea7\u54c1',ssUrlPrefix + 'products/oss/mbb/index.htm');
g_navNode_7_7_2_0=g_navNode_7_7_2.addNode('590','iManager M2000',ssUrlPrefix + 'products/oss/mbb/imanager-m2000/index.htm');
g_navNode_7_7_2_2=g_navNode_7_7_2.addNode('592','iManager PRS',ssUrlPrefix + 'products/oss/mbb/imanager-prs/index.htm');
g_navNode_7_7_2_4=g_navNode_7_7_2.addNode('6685','iManager TranSight',ssUrlPrefix + 'products/oss/mbb/imanager-transight/index.htm');
g_navNode_7_8=g_navNode_7.addNode('6687','\u670d\u52a1\u5668',ssUrlPrefix + 'products/servers/index.htm');
g_navNode_7_9=g_navNode_7.addNode('658','\u5b58\u50a8',ssUrlPrefix + 'products/storage/index.htm');
g_navNode_7_10=g_navNode_7.addNode('18','\u7ec8\u7aef',ssUrlPrefix + 'products/devices/index.htm','externalUrl==http\x3a//consumer.huawei.com/cn/');
g_navNode_8=g_navNode_Root.addNode('9','\u670d\u52a1',ssUrlPrefix + 'services/index.htm','secondaryUrlVariableField==article');
g_navNode_9=g_navNode_Root.addNode('25','\u5173\u4e8e\u534e\u4e3a',ssUrlPrefix + 'about-huawei/index.htm');
g_navNode_9_0=g_navNode_9.addNode('166','\u516c\u53f8\u4ecb\u7ecd',ssUrlPrefix + 'about-huawei/corporate-info/index.htm');
g_navNode_9_0_0=g_navNode_9_0.addNode('171','\u6838\u5fc3\u4ef7\u503c\u89c2',ssUrlPrefix + 'about-huawei/corporate-info/core-values/index.htm');
g_navNode_9_0_1=g_navNode_9_0.addNode('172','\u4ef7\u503c\u4e3b\u5f20',ssUrlPrefix + 'about-huawei/corporate-info/value-propositions/index.htm');
g_navNode_9_0_2=g_navNode_9_0.addNode('173','\u8d22\u52a1\u6982\u8981',ssUrlPrefix + 'about-huawei/corporate-info/financial-highlights/index.htm');
g_navNode_9_0_3=g_navNode_9_0.addNode('174','\u516c\u53f8\u6cbb\u7406',ssUrlPrefix + 'about-huawei/corporate-info/corporate-governance/index.htm');
g_navNode_9_0_3_0=g_navNode_9_0_3.addNode('10603','\u80a1\u4e1c\u4f1a\u548c\u6301\u80a1\u5458\u5de5\u4ee3\u8868\u4f1a',ssUrlPrefix + 'about-huawei/corporate-info/corporate-governance/shareholders/index.htm');
g_navNode_9_0_3_1=g_navNode_9_0_3.addNode('10597','\u8463\u4e8b\u4f1a',ssUrlPrefix + 'about-huawei/corporate-info/corporate-governance/board-of-directors/index.htm');
g_navNode_9_0_3_2=g_navNode_9_0_3.addNode('10598','\u8463\u4e8b\u4f1a\u4e13\u4e1a\u59d4\u5458\u4f1a',ssUrlPrefix + 'about-huawei/corporate-info/corporate-governance/board-committees/index.htm');
g_navNode_9_0_3_3=g_navNode_9_0_3.addNode('10599','\u76d1\u4e8b\u4f1a',ssUrlPrefix + 'about-huawei/corporate-info/corporate-governance/supervisory-board/index.htm');
g_navNode_9_0_3_4=g_navNode_9_0_3.addNode('10600','\u8f6e\u503cCEO',ssUrlPrefix + 'about-huawei/corporate-info/corporate-governance/rotating-ceos/index.htm');
g_navNode_9_0_3_5=g_navNode_9_0_3.addNode('10601','\u72ec\u7acb\u5ba1\u8ba1\u5e08',ssUrlPrefix + 'about-huawei/corporate-info/corporate-governance/independent-auditor/index.htm');
g_navNode_9_0_3_6=g_navNode_9_0_3.addNode('10602','\u5185\u90e8\u63a7\u5236',ssUrlPrefix + 'about-huawei/corporate-info/corporate-governance/internal-control/index.htm');
g_navNode_9_0_4=g_navNode_9_0.addNode('175','\u7814\u7a76\u5f00\u53d1',ssUrlPrefix + 'about-huawei/corporate-info/research-development/index.htm');
g_navNode_9_0_5=g_navNode_9_0.addNode('2180','\u7f51\u7edc\u5b89\u5168',ssUrlPrefix + 'about-huawei/corporate-info/cyber_security/index.htm');
g_navNode_9_0_6=g_navNode_9_0.addNode('176','\u53d1\u5c55\u5386\u7a0b',ssUrlPrefix + 'about-huawei/corporate-info/milestone/index.htm');
g_navNode_9_0_7=g_navNode_9_0.addNode('222','\u8d28\u91cf\u65b9\u9488',ssUrlPrefix + 'about-huawei/corporate-info/quality-policy/index.htm');
g_navNode_9_0_8=g_navNode_9_0.addNode('177','\u516c\u53f8\u5e74\u62a5',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/index.htm','externalUrl==/cn/about-huawei/corporate-info/annual-report/2013/index.htm');
g_navNode_9_0_8_0=g_navNode_9_0_8.addNode('9387','\u534e\u4e3a2013\u5e74\u5e74\u62a5',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/index.htm');
g_navNode_9_0_8_0_0=g_navNode_9_0_8_0.addNode('9388','CEO\u81f4\u8f9e',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/letter-from-the-ceo/index.htm');
g_navNode_9_0_8_0_1=g_navNode_9_0_8_0.addNode('9487','\u8f6e\u503cCEO\u81f4\u8f9e',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/letter-from-rotating-and-acting-ceo/index.htm');
g_navNode_9_0_8_0_2=g_navNode_9_0_8_0.addNode('9389','2013\u5e74\u4e1a\u52a1\u8fdb\u5c55',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/business-highlights-2013/index.htm');
g_navNode_9_0_8_0_3=g_navNode_9_0_8_0.addNode('9397','5\u5e74\u8d22\u52a1\u6982\u8981',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/five-year-financial-highlights/index.htm');
g_navNode_9_0_8_0_4=g_navNode_9_0_8_0.addNode('9782','\u8463\u4e8b\u957f\u81f4\u8f9e',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/letter-from-chairwoman/index.htm');
g_navNode_9_0_8_0_5=g_navNode_9_0_8_0.addNode('9489','\u6838\u5fc3\u4ef7\u503c\u89c2',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/core-values/index.htm');
g_navNode_9_0_8_0_6=g_navNode_9_0_8_0.addNode('9491','\u7ba1\u7406\u5c42\u8ba8\u8bba\u4e0e\u5206\u6790',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/management-discussion/index.htm','externalUrl==/cn/about-huawei/corporate-info/annual-report/2013/management-discussion/value-propositions/index.htm');
g_navNode_9_0_8_0_6_0=g_navNode_9_0_8_0_6.addNode('10519','\u6211\u4eec\u7684\u4ef7\u503c\u4e3b\u5f20',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/management-discussion/value-propositions/index.htm');
g_navNode_9_0_8_0_6_1=g_navNode_9_0_8_0_6.addNode('9637','2013\u5e74\u4e1a\u52a1\u56de\u987e',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/management-discussion/business-review-2013/index.htm');
g_navNode_9_0_8_0_6_1_0=g_navNode_9_0_8_0_6_1.addNode('9639','\u8fd0\u8425\u5546\u7f51\u7edc',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/management-discussion/business-review-2013/carrier-network/index.htm');
g_navNode_9_0_8_0_6_1_1=g_navNode_9_0_8_0_6_1.addNode('9770','\u4f01\u4e1a\u4e1a\u52a1',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/management-discussion/business-review-2013/enterprise-business/index.htm');
g_navNode_9_0_8_0_6_1_2=g_navNode_9_0_8_0_6_1.addNode('9771','\u6d88\u8d39\u8005\u4e1a\u52a1',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/management-discussion/business-review-2013/consumer-business/index.htm');
g_navNode_9_0_8_0_6_2=g_navNode_9_0_8_0_6.addNode('9493','\u7ecf\u8425\u6210\u679c',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/management-discussion/results-of-operations/index.htm');
g_navNode_9_0_8_0_6_3=g_navNode_9_0_8_0_6.addNode('9786','\u8d22\u52a1\u72b6\u51b5',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/management-discussion/financial-position/index.htm');
g_navNode_9_0_8_0_6_4=g_navNode_9_0_8_0_6.addNode('9793','\u7ecf\u8425\u6d3b\u52a8\u73b0\u91d1\u6d41',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/management-discussion/cash-flow/index.htm');
g_navNode_9_0_8_0_6_5=g_navNode_9_0_8_0_6.addNode('9802','\u8d22\u52a1\u98ce\u9669\u7ba1\u7406',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/management-discussion/financial-risk-management/index.htm');
g_navNode_9_0_8_0_6_6=g_navNode_9_0_8_0_6.addNode('9805','\u7814\u7a76\u4e0e\u5f00\u53d1',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/management-discussion/research-and-development/index.htm');
g_navNode_9_0_8_0_6_7=g_navNode_9_0_8_0_6.addNode('9806','\u7f51\u7edc\u5b89\u5168',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/management-discussion/cyber-security/index.htm');
g_navNode_9_0_8_0_6_8=g_navNode_9_0_8_0_6.addNode('9807','\u5173\u952e\u4f1a\u8ba1\u4f30\u8ba1',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/management-discussion/critical-accounting-estimates/index.htm');
g_navNode_9_0_8_0_7=g_navNode_9_0_8_0.addNode('9772','\u884c\u4e1a\u8d8b\u52bf',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/market-trends/index.htm');
g_navNode_9_0_8_0_8=g_navNode_9_0_8_0.addNode('9773','\u72ec\u7acb\u5ba1\u8ba1\u5e08\u62a5\u544a',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/auditor-report/index.htm');
g_navNode_9_0_8_0_9=g_navNode_9_0_8_0.addNode('9774','\u5408\u5e76\u8d22\u52a1\u62a5\u8868\u6458\u8981\u53ca\u9644\u6ce8',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/consolidated-financial-statements-summary/index.htm');
g_navNode_9_0_8_0_10=g_navNode_9_0_8_0.addNode('9776','\u98ce\u9669\u8981\u7d20',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/risk-factors/index.htm');
g_navNode_9_0_8_0_11=g_navNode_9_0_8_0.addNode('9777','\u516c\u53f8\u6cbb\u7406\u62a5\u544a',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/corporate-governance-report/index.htm');
g_navNode_9_0_8_0_12=g_navNode_9_0_8_0.addNode('9778','\u53ef\u6301\u7eed\u53d1\u5c55',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/sustainable-development/index.htm');
g_navNode_9_0_8_0_13=g_navNode_9_0_8_0.addNode('9785','\u82f1\u6587\u7f29\u7565\u8bed\u3001\u8d22\u52a1\u672f\u8bed\u4e0e\u6c47\u7387',ssUrlPrefix + 'about-huawei/corporate-info/annual-report/2013/abbreviations/index.htm');
g_navNode_9_1=g_navNode_9.addNode('10531','\u53ef\u6301\u7eed\u53d1\u5c55',ssUrlPrefix + 'about-huawei/sustainability/index.htm');
g_navNode_9_1_0=g_navNode_9_1.addNode('15826','\u9886\u5bfc\u529b',ssUrlPrefix + 'about-huawei/sustainability/leadership/index.htm');
g_navNode_9_1_0_0=g_navNode_9_1_0.addNode('10580','\u8463\u4e8b\u957f\u81f4\u8f9e',ssUrlPrefix + 'about-huawei/sustainability/leadership/message-from-chairwoman/index.htm');
g_navNode_9_1_0_1=g_navNode_9_1_0.addNode('15828','\u8f6e\u503cCEO\u81f4\u8f9e',ssUrlPrefix + 'about-huawei/sustainability/leadership/message-from-rotating-and-acting-ceo/index.htm');
g_navNode_9_1_0_2=g_navNode_9_1_0.addNode('10586','\u53ef\u6301\u7eed\u53d1\u5c55\u59d4\u5458\u4f1a\u4e3b\u4efb\u81f4\u8f9e',ssUrlPrefix + 'about-huawei/sustainability/leadership/message-from-csd-chairman/index.htm');
g_navNode_9_1_1=g_navNode_9_1.addNode('10532','\u53ef\u6301\u7eed\u53d1\u5c55\u7ba1\u7406',ssUrlPrefix + 'about-huawei/sustainability/management/index.htm');
g_navNode_9_1_1_0=g_navNode_9_1_1.addNode('10587','\u53ef\u6301\u7eed\u53d1\u5c55\u6218\u7565\x26\u7ec4\u7ec7',ssUrlPrefix + 'about-huawei/sustainability/management/strategy-organization/index.htm');
g_navNode_9_1_1_1=g_navNode_9_1_1.addNode('10588','\u53ef\u6301\u7eed\u53d1\u5c55\u7ba1\u7406\u4f53\u7cfb',ssUrlPrefix + 'about-huawei/sustainability/management/management-system/index.htm');
g_navNode_9_1_2=g_navNode_9_1.addNode('10555','\u6d88\u9664\u6570\u5b57\u9e3f\u6c9f',ssUrlPrefix + 'about-huawei/sustainability/digital-divide/index.htm');
g_navNode_9_1_2_0=g_navNode_9_1_2.addNode('10559','\u4eba\u4eba\u4eab\u6709\u901a\u4fe1',ssUrlPrefix + 'about-huawei/sustainability/digital-divide/communications-for-all/index.htm');
g_navNode_9_1_2_1=g_navNode_9_1_2.addNode('10560','\u4eba\u4eba\u4eab\u6709\u5bbd\u5e26',ssUrlPrefix + 'about-huawei/sustainability/digital-divide/broadband-for-all/index.htm');
g_navNode_9_1_2_2=g_navNode_9_1_2.addNode('10562','ICT\u6280\u672f\u5e94\u7528',ssUrlPrefix + 'about-huawei/sustainability/digital-divide/application-ict/index.htm');
g_navNode_9_1_2_3=g_navNode_9_1_2.addNode('10561','ICT\u4eba\u624d\u57f9\u517b',ssUrlPrefix + 'about-huawei/sustainability/digital-divide/nurturing-ict-talent/index.htm');
g_navNode_9_1_3=g_navNode_9_1.addNode('10556','\u4fdd\u969c\u7f51\u7edc\u5b89\u5168\u7a33\u5b9a\u8fd0\u884c',ssUrlPrefix + 'about-huawei/sustainability/stable-secure-network/index.htm');
g_navNode_9_1_3_0=g_navNode_9_1_3.addNode('10563','\u7f51\u7edc\u7a33\u5b9a',ssUrlPrefix + 'about-huawei/sustainability/stable-secure-network/network-stability/index.htm');
g_navNode_9_1_3_1=g_navNode_9_1_3.addNode('10564','\u7f51\u7edc\u5b89\u5168',ssUrlPrefix + 'about-huawei/sustainability/stable-secure-network/cyber-security/index.htm');
g_navNode_9_1_4=g_navNode_9_1.addNode('10557','\u63a8\u8fdb\u7eff\u8272\u73af\u4fdd',ssUrlPrefix + 'about-huawei/sustainability/environment-protect/index.htm');
g_navNode_9_1_4_0=g_navNode_9_1_4.addNode('10565','\u7eff\u8272\u4ea7\u54c1\u4e0e\u670d\u52a1',ssUrlPrefix + 'about-huawei/sustainability/environment-protect/green-products-services/index.htm');
g_navNode_9_1_4_1=g_navNode_9_1_4.addNode('10566','\u80fd\u6e90\u4e0e\u6c14\u5019\u53d8\u5316',ssUrlPrefix + 'about-huawei/sustainability/environment-protect/climate-change/index.htm');
g_navNode_9_1_4_2=g_navNode_9_1_4.addNode('10567','\u8d44\u6e90\u6548\u7387',ssUrlPrefix + 'about-huawei/sustainability/environment-protect/sustainable-resources/index.htm');
g_navNode_9_1_4_3=g_navNode_9_1_4.addNode('10568','\u5faa\u73af\u7ecf\u6d4e',ssUrlPrefix + 'about-huawei/sustainability/environment-protect/pollution-prevent/index.htm');
g_navNode_9_1_5=g_navNode_9_1.addNode('10558','\u5b9e\u73b0\u5171\u540c\u53d1\u5c55',ssUrlPrefix + 'about-huawei/sustainability/win-win-development/index.htm');
g_navNode_9_1_5_0=g_navNode_9_1_5.addNode('10572','\u5458\u5de5\u5173\u7231',ssUrlPrefix + 'about-huawei/sustainability/win-win-development/caring-employees/index.htm');
g_navNode_9_1_5_1=g_navNode_9_1_5.addNode('10575','\u793e\u4f1a\u516c\u76ca',ssUrlPrefix + 'about-huawei/sustainability/win-win-development/social-contribution/index.htm');
g_navNode_9_1_5_2=g_navNode_9_1_5.addNode('10573','\u5ba2\u6237\u6ee1\u610f\u5ea6\u7ba1\u7406',ssUrlPrefix + 'about-huawei/sustainability/win-win-development/applicable-laws-and-regulations/index.htm');
g_navNode_9_1_5_3=g_navNode_9_1_5.addNode('10593','\u5065\u5eb7\u4e0e\u5b89\u5168',ssUrlPrefix + 'about-huawei/sustainability/win-win-development/focus-own-risk/index.htm');
g_navNode_9_1_5_4=g_navNode_9_1_5.addNode('10574','\u4f9b\u5e94\u94fe\u53ef\u6301\u7eed\u53d1\u5c55',ssUrlPrefix + 'about-huawei/sustainability/win-win-development/sustainable-supply-chain/index.htm');
g_navNode_9_1_6=g_navNode_9_1.addNode('34','\u53ef\u6301\u7eed\u53d1\u5c55\u62a5\u544a',ssUrlPrefix + 'about-huawei/sustainability/sustainability-report/index.htm');
g_navNode_9_4=g_navNode_9.addNode('27','\u516c\u53f8\u520a\u7269',ssUrlPrefix + 'about-huawei/publications/index.htm');
g_navNode_9_4_0=g_navNode_9_4.addNode('28','\u300a\u8425\u8d62\u300b',ssUrlPrefix + 'about-huawei/publications/winwin-magazine/index.htm','secondaryUrlVariableField==winwinContent');
g_navNode_9_4_1=g_navNode_9_4.addNode('198','\u300a\u534e\u4e3a\u6280\u672f\u300b',ssUrlPrefix + 'about-huawei/publications/communicate/index.htm','secondaryUrlVariableField==winwinContent');
g_navNode_9_4_3=g_navNode_9_4.addNode('200','\u300a\u534e\u4e3a\u4eba\u300b',ssUrlPrefix + 'about-huawei/publications/huawei-people/index.htm','externalUrl==http\x3a//app.huawei.com/paper/index.do');
g_navNode_9_5=g_navNode_9.addNode('26','\u5c55\u4f1a\u6d3b\u52a8',ssUrlPrefix + 'about-huawei/events/index.htm','secondaryUrlVariableField==eventarticle');
g_navNode_9_5_0=g_navNode_9_5.addNode('190','\u5f80\u671f\u5c55\u4f1a',ssUrlPrefix + 'about-huawei/events/previous-events/index.htm','secondaryUrlVariableField==Eventscontent');
g_navNode_9_6=g_navNode_9.addNode('170','\u5408\u4f5c\u4f19\u4f34',ssUrlPrefix + 'about-huawei/Partner/index.htm');
g_navNode_9_6_0=g_navNode_9_6.addNode('380','\u8fd0\u8425\u5546\u7f51\u7edc\u4e1a\u52a1\u5408\u4f5c',ssUrlPrefix + 'about-huawei/Partner/integrated-oss/index.htm');
g_navNode_9_6_0_0=g_navNode_9_6_0.addNode('384','OSS \u5bf9\u63a5\u4fe1\u606f',ssUrlPrefix + 'about-huawei/Partner/integrated-oss/inter-operability/index.htm');
g_navNode_9_6_0_1=g_navNode_9_6_0.addNode('385','OSS\u5408\u4f5c\u4f19\u4f34',ssUrlPrefix + 'about-huawei/Partner/integrated-oss/oss_partners/index.htm');
g_navNode_9_6_1=g_navNode_9_6.addNode('381','\u4f01\u4e1a\u4e1a\u52a1\u5408\u4f5c',ssUrlPrefix + 'about-huawei/Partner/enterprise/index.htm','externalUrl==http\x3a//enterprise.huawei.com/cn/partners/index.htm');
g_navNode_9_7=g_navNode_9.addNode('197','\u4f9b\u5e94\u5546',ssUrlPrefix + 'about-huawei/suppliers/index.htm');
g_navNode_9_7_0=g_navNode_9_7.addNode('11125','\u534e\u4e3a\u91c7\u8d2d\u514d\u8d23\u58f0\u660e',ssUrlPrefix + 'about-huawei/suppliers/procurement-disclaimer/index.htm');
g_navNode_9_7_1=g_navNode_9_7.addNode('12650','\u534e\u4e3a\u4f9b\u5e94\u5546\u8ba4\u8bc1\u57fa\u7840\u534f\u8bae',ssUrlPrefix + 'about-huawei/suppliers/Basic-agreement-for-huawei-supplier-qualification/index.htm');
g_navNode_9_7_2=g_navNode_9_7.addNode('790','\u65b0\u5e74\u8d3a\u8bcd',ssUrlPrefix + 'about-huawei/suppliers/new-year-greetings/index.htm','secondaryUrlVariableField==article');
g_navNode_9_7_3=g_navNode_9_7.addNode('791','\u81f4\u4f9b\u5e94\u5546\u7684\u516c\u5f00\u4fe1',ssUrlPrefix + 'about-huawei/suppliers/open-letter/index.htm','secondaryUrlVariableField==article');
g_navNode_9_7_4=g_navNode_9_7.addNode('792','\u5bf9\u201c\u4f9b\u5e94\u5546\u5047\u501f\u516c\u53f8\u5404\u7ea7\u4e3b\u7ba1\u540d\u4e49\u6765\u5f71\u54cd\u91c7\u8d2d\u4e1a\u52a1\u201d\u7684\u5904\u7406\u6307\u5bfc\u610f\u89c1',ssUrlPrefix + 'about-huawei/suppliers/sanction/index.htm','secondaryUrlVariableField==article');
g_navNode_9_7_5=g_navNode_9_7.addNode('793','\u4f9b\u5e94\u5546\u987b\u77e5',ssUrlPrefix + 'about-huawei/suppliers/notice/index.htm','secondaryUrlVariableField==article');
g_navNode_9_7_6=g_navNode_9_7.addNode('794','\u91c7\u8d2d\u52a8\u6001',ssUrlPrefix + 'about-huawei/suppliers/procurement/index.htm','secondaryUrlVariableField==article');
g_navNode_9_7_7=g_navNode_9_7.addNode('795','\u6f5c\u5728\u4f9b\u5e94\u5546\u767b\u8bb0',ssUrlPrefix + 'about-huawei/suppliers/register/index.htm','secondaryUrlVariableField==article');
g_navNode_9_7_8=g_navNode_9_7.addNode('796','\u53d1\u73b0\u4f9b\u5e94\u5546\u8fdd\u53cd\u5546\u4e1a\u884c\u4e3a\u51c6\u5219\u7684\u53ca\u65f6\u62a5\u544a\u548c\u5904\u7406\u6307\u5f15',ssUrlPrefix + 'about-huawei/suppliers/business-rules/index.htm','secondaryUrlVariableField==article');
g_navNode_9_7_9=g_navNode_9_7.addNode('797','\u6295\u8bc9\u4e0e\u53cd\u9988',ssUrlPrefix + 'about-huawei/suppliers/feedback/index.htm','secondaryUrlVariableField==article');
g_navNode_9_7_10=g_navNode_9_7.addNode('798','\u91c7\u8d2d\u7cfb\u7edf\u94fe\u63a5',ssUrlPrefix + 'about-huawei/suppliers/e-bidding/index.htm','secondaryUrlVariableField==article');
g_navNode_9_7_11=g_navNode_9_7.addNode('11133','\u91c7\u8d2d\u4e1a\u52a1\u884c\u4e3a\u51c6\u5219',ssUrlPrefix + 'about-huawei/suppliers/procurement-business-conduct-guidelines/index.htm');
g_navNode_9_8=g_navNode_9.addNode('263','Newsletter',ssUrlPrefix + 'about-huawei/newsletter/index.htm','contributorOnly==false','externalUrl==http\x3a//www.huawei.com/newsletter/SubscribeNL_init.html?form.subscribeCategory\x3dChinese\x26request_locale\x3dzh_CN','isDynamic==FALSE');
g_navNode_9_8_0=g_navNode_9_8.addNode('264','\u8ba2\u9605',ssUrlPrefix + 'about-huawei/newsletter/subscribe/index.htm','externalUrl==http\x3a//www.huawei.com/newsletter/SubscribeNL_init.html?form.subscribeCategory\x3dChinese\x26request_locale\x3dzh_CN');
g_navNode_9_8_1=g_navNode_9_8.addNode('266','\u9000\u8ba2',ssUrlPrefix + 'about-huawei/newsletter/unsubscribe/index.htm','externalUrl==http\x3a//www.huawei.com/newsletter/UnsubscribeNL_init.html?request_locale\x3dzh_CN');
g_navNode_9_9=g_navNode_9.addNode('163','RSS',ssUrlPrefix + 'about-huawei/rss-feeds/index.htm');
g_navNode_9_10=g_navNode_9.addNode('35','\u8054\u7cfb\u6211\u4eec',ssUrlPrefix + 'about-huawei/contact-us/index.htm');
g_navNode_9_10_0=g_navNode_9_10.addNode('39','\u4e2d\u56fd',ssUrlPrefix + 'about-huawei/contact-us/china/index.htm');
g_navNode_9_10_1=g_navNode_9_10.addNode('37','\u4e9a\u592a',ssUrlPrefix + 'about-huawei/contact-us/asia-pacific/index.htm');
g_navNode_9_10_2=g_navNode_9_10.addNode('38','\u72ec\u8054\u4f53',ssUrlPrefix + 'about-huawei/contact-us/cis/index.htm');
g_navNode_9_10_3=g_navNode_9_10.addNode('40','\u6b27\u6d32',ssUrlPrefix + 'about-huawei/contact-us/europe/index.htm');
g_navNode_9_10_4=g_navNode_9_10.addNode('41','\u62c9\u4e01\u7f8e\u6d32',ssUrlPrefix + 'about-huawei/contact-us/latin-america/index.htm');
g_navNode_9_10_5=g_navNode_9_10.addNode('42','\u4e2d\u4e1c\u5317\u975e',ssUrlPrefix + 'about-huawei/contact-us/mena/index.htm');
g_navNode_9_10_6=g_navNode_9_10.addNode('43','\u5317\u7f8e',ssUrlPrefix + 'about-huawei/contact-us/north-america/index.htm');
g_navNode_9_10_7=g_navNode_9_10.addNode('44','\u5357\u90e8\u975e\u6d32',ssUrlPrefix + 'about-huawei/contact-us/south-africa/index.htm');
g_navNode_9_10_8=g_navNode_9_10.addNode('36','\u5168\u7403\u5206\u652f\u673a\u6784',ssUrlPrefix + 'about-huawei/contact-us/a-z/index.htm');
g_navNode_10=g_navNode_Root.addNode('219','\u6210\u529f\u6545\u4e8b',ssUrlPrefix + 'success-story/index.htm','secondaryUrlVariableField==article');
g_navNode_13=g_navNode_Root.addNode('299','\u4ea7\u54c1\u751f\u547d\u5468\u671f',ssUrlPrefix + 'ProductsLifecycle/index.htm');
g_navNode_13_0=g_navNode_13.addNode('300','\u65e0\u7ebf\u63a5\u5165\u7f51\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/RadioAccessProducts/index.htm');
g_navNode_13_0_0=g_navNode_13_0.addNode('321','PS\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/RadioAccessProducts/PSProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_0_1=g_navNode_13_0.addNode('308','GSM BSS\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/RadioAccessProducts/GSMBSSProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_0_2=g_navNode_13_0.addNode('3808','SingleRAN\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/RadioAccessProducts/SingleRan/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_0_3=g_navNode_13_0.addNode('309','UMTS RAN\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/RadioAccessProducts/UMTSRANProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_0_5=g_navNode_13_0.addNode('310','CDMA BSS\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/RadioAccessProducts/CDMABSSProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_0_6=g_navNode_13_0.addNode('311','OSS\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/RadioAccessProducts/OSSProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_0_7=g_navNode_13_0.addNode('254','WiMAX\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/RadioAccessProducts/WiMAXProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_0_8=g_navNode_13_0.addNode('1008','LTE\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/RadioAccessProducts/LTE-Products/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_0_9=g_navNode_13_0.addNode('10855','Small Cell\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/RadioAccessProducts/small-cell/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_0_10=g_navNode_13_0.addNode('11446','\u516c\u5171\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/RadioAccessProducts/PublicProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_1=g_navNode_13.addNode('301','\u6838\u5fc3\u7f51\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/CoreNetworkProducts/index.htm');
g_navNode_13_1_0=g_navNode_13_1.addNode('312','GSM/UMTS CS ',ssUrlPrefix + 'ProductsLifecycle/CoreNetworkProducts/GSMUMTSCSProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_1_1=g_navNode_13_1.addNode('313','CDMA CS',ssUrlPrefix + 'ProductsLifecycle/CoreNetworkProducts/CDMACSProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_1_2=g_navNode_13_1.addNode('314','NGN',ssUrlPrefix + 'ProductsLifecycle/CoreNetworkProducts/NGNProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_1_3=g_navNode_13_1.addNode('315','IMS',ssUrlPrefix + 'ProductsLifecycle/CoreNetworkProducts/IMSProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_1_4=g_navNode_13_1.addNode('317','Switch',ssUrlPrefix + 'ProductsLifecycle/CoreNetworkProducts/SwitchProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_1_5=g_navNode_13_1.addNode('318','HLR',ssUrlPrefix + 'ProductsLifecycle/CoreNetworkProducts/HLRProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_1_6=g_navNode_13_1.addNode('319','MGW',ssUrlPrefix + 'ProductsLifecycle/CoreNetworkProducts/MGWProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_1_7=g_navNode_13_1.addNode('815','SG/STP',ssUrlPrefix + 'ProductsLifecycle/CoreNetworkProducts/STP/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_1_8=g_navNode_13_1.addNode('10796','UPCC',ssUrlPrefix + 'ProductsLifecycle/CoreNetworkProducts/upcc/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_1_9=g_navNode_13_1.addNode('316','Others',ssUrlPrefix + 'ProductsLifecycle/CoreNetworkProducts/others/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_2=g_navNode_13.addNode('302','\u4f20\u9001\u7f51\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/TransportNetworkProducts/index.htm');
g_navNode_13_2_0=g_navNode_13_2.addNode('323','\u6ce2\u5206\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/TransportNetworkProducts/WDMOTNProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_2_1=g_navNode_13_2.addNode('324','MSTP\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/TransportNetworkProducts/MSTPProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_2_2=g_navNode_13_2.addNode('325','\u5fae\u6ce2\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/TransportNetworkProducts/MicrowaveProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_3=g_navNode_13.addNode('303','\u63a5\u5165\u7f51\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/BroadbandAccessProducts/index.htm');
g_navNode_13_3_0=g_navNode_13_3.addNode('326','DSLAM\u7cfb\u5217',ssUrlPrefix + 'ProductsLifecycle/BroadbandAccessProducts/DSLAMProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_3_1=g_navNode_13_3.addNode('327','MSAN\u7cfb\u5217',ssUrlPrefix + 'ProductsLifecycle/BroadbandAccessProducts/MSANProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_3_2=g_navNode_13_3.addNode('328','FTTx\u7cfb\u5217',ssUrlPrefix + 'ProductsLifecycle/BroadbandAccessProducts/FTTXProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_3_3=g_navNode_13_3.addNode('329','ODN\u7cfb\u5217',ssUrlPrefix + 'ProductsLifecycle/BroadbandAccessProducts/ODNProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_3_4=g_navNode_13_3.addNode('269','BITS\u7cfb\u5217',ssUrlPrefix + 'ProductsLifecycle/BroadbandAccessProducts/BITSProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_4=g_navNode_13.addNode('304','\u4e1a\u52a1\u4e0e\u8f6f\u4ef6',ssUrlPrefix + 'ProductsLifecycle/ApplicationSoftware/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_5=g_navNode_13.addNode('305','\u6570\u636e\u901a\u4fe1\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/DataCommunicationsProducts/index.htm');
g_navNode_13_5_0=g_navNode_13_5.addNode('330','NE\u7cfb\u5217\u8def\u7531\u5668',ssUrlPrefix + 'ProductsLifecycle/DataCommunicationsProducts/NESeriesRoutersProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_5_1=g_navNode_13_5.addNode('331','AR\u7cfb\u5217\u8def\u7531\u5668',ssUrlPrefix + 'ProductsLifecycle/DataCommunicationsProducts/ARSeriesRoutersProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_5_2=g_navNode_13_5.addNode('332','MSCG\u591a\u4e1a\u52a1\u63a7\u5236\u7f51\u5173',ssUrlPrefix + 'ProductsLifecycle/DataCommunicationsProducts/Multi-serviceControlGatewayProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_5_3=g_navNode_13_5.addNode('333','CX\u7cfb\u5217\u57ce\u57df\u4e1a\u52a1\u5e73\u53f0',ssUrlPrefix + 'ProductsLifecycle/DataCommunicationsProducts/MetroServicesPlatform/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_5_4=g_navNode_13_5.addNode('813','SRG\u7cfb\u5217\u4e1a\u52a1\u8def\u7531\u7f51\u5173',ssUrlPrefix + 'ProductsLifecycle/DataCommunicationsProducts/SRGSeriesRoutersGateway/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_5_5=g_navNode_13_5.addNode('334','\u4ee5\u592a\u7f51\u4ea4\u6362\u673a',ssUrlPrefix + 'ProductsLifecycle/DataCommunicationsProducts/EthernetSwitchesProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_5_6=g_navNode_13_5.addNode('335','\u7f51\u7edc\u5b89\u5168\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/DataCommunicationsProducts/NetworkSecurityProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_5_7=g_navNode_13_5.addNode('336','PTN',ssUrlPrefix + 'ProductsLifecycle/DataCommunicationsProducts/PTN/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_6=g_navNode_13.addNode('306','OSS\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/OSSProducts/index.htm');
g_navNode_13_6_0=g_navNode_13_6.addNode('337','\u7f51\u7edc\u7edf\u4e00\u7f51\u7ba1\uff08U2000\uff09',ssUrlPrefix + 'ProductsLifecycle/OSSProducts/iManagerU2000/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_6_1=g_navNode_13_6.addNode('338','\u4f20\u9001\u7f51\u7ba1\uff08T2000/2100\uff09',ssUrlPrefix + 'ProductsLifecycle/OSSProducts/iManagerT2000-2100/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_6_2=g_navNode_13_6.addNode('339','\u8def\u7531\u5668\u7f51\u7ba1\uff08DMS\uff09 ',ssUrlPrefix + 'ProductsLifecycle/OSSProducts/iManagerN2000DMSProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_6_3=g_navNode_13_6.addNode('340','\u63a5\u5165\u7f51\u7ba1\uff08BMS\uff09',ssUrlPrefix + 'ProductsLifecycle/OSSProducts/iManagerN2000BMSProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_6_4=g_navNode_13_6.addNode('341','\u589e\u503c\u8f6f\u4ef6\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/OSSProducts/Value-AddedSoftwareProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_6_5=g_navNode_13_6.addNode('342','\u6838\u5fc3\u7f51\u7f51\u7ba1\uff08N2000\uff09',ssUrlPrefix + 'ProductsLifecycle/OSSProducts/iManagerN2000Products/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_6_6=g_navNode_13_6.addNode('343','\u65e0\u7ebf\u7f51\u7ba1\uff08M2000\uff09',ssUrlPrefix + 'ProductsLifecycle/OSSProducts/iManagerM2000Products/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_7=g_navNode_13.addNode('307','\u7ad9\u70b9\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/SiteProducts/index.htm');
g_navNode_13_7_0=g_navNode_13_7.addNode('814','\u5929\u9988\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/SiteProducts/AntennaProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_13_7_1=g_navNode_13_7.addNode('344','\u914d\u7ebf\u67b6\u4ea7\u54c1',ssUrlPrefix + 'ProductsLifecycle/SiteProducts/MDFProducts/index.htm','secondaryUrlVariableField==lifecycleContent');
g_navNode_28=g_navNode_Root.addNode('7224','Security',ssUrlPrefix + 'security/index.htm');
g_navNode_28_0=g_navNode_28.addNode('7225','PSIRT',ssUrlPrefix + 'security/psirt/index.htm');
g_navNode_28_0_0=g_navNode_28_0.addNode('7228','\u5b89\u5168\u901a\u544a',ssUrlPrefix + 'security/psirt/security-bulletins/index.htm','secondaryUrlVariableField==article');
g_navNode_28_0_0_1=g_navNode_28_0_0.addNode('9634','\u5b89\u5168\u9884\u8b66',ssUrlPrefix + 'security/psirt/security-bulletins/security-advisories/index.htm','secondaryUrlVariableField==article');
g_navNode_28_0_0_2=g_navNode_28_0_0.addNode('9635','\u5b89\u5168\u516c\u544a',ssUrlPrefix + 'security/psirt/security-bulletins/security-notices/index.htm','secondaryUrlVariableField==article');
g_navNode_28_0_1=g_navNode_28_0.addNode('7252','\u6f0f\u6d1e\u54cd\u5e94\u6d41\u7a0b',ssUrlPrefix + 'security/psirt/vul-response-process/index.htm');
g_navNode_28_0_2=g_navNode_28_0.addNode('7253','\u6f0f\u6d1e\u4e0a\u62a5',ssUrlPrefix + 'security/psirt/report-vulnerabilities/index.htm');
g_navNode_28_0_3=g_navNode_28_0.addNode('7251','\u534e\u4e3aPSIRT',ssUrlPrefix + 'security/psirt/about-huawei-psirt/index.htm');
g_navNode_31=g_navNode_Root.addNode('11365','error',ssUrlPrefix + 'error/index.htm');
g_navNode_33=g_navNode_Root.addNode('12574','\u52a0\u5165\u534e\u4e3a',ssUrlPrefix + 'career/index.htm');
g_navNode_33_0=g_navNode_33.addNode('12547','\u6821\u56ed\u62db\u8058',ssUrlPrefix + 'career/campus/index.htm');
g_navNode_33_0_0=g_navNode_33_0.addNode('12549','\u62db\u8058\u52a8\u6001',ssUrlPrefix + 'career/campus/news/index.htm','secondaryUrlVariableField==article');
g_navNode_33_0_1=g_navNode_33_0.addNode('12583','\u6210\u957f\u5728\u534e\u4e3a',ssUrlPrefix + 'career/campus/grow-with-huawei/index.htm','secondaryUrlVariableField==article');
g_navNode_33_0_2=g_navNode_33_0.addNode('12584','\u534e\u4e3a\u65b0\u7cbe\u5f69',ssUrlPrefix + 'career/campus/brilliant-huawei/index.htm','secondaryUrlVariableField==article');
g_navNode_33_1=g_navNode_33.addNode('12548','\u793e\u4f1a\u62db\u8058',ssUrlPrefix + 'career/social/index.htm');
g_navNode_33_1_0=g_navNode_33_1.addNode('12634','\u62db\u8058\u52a8\u6001',ssUrlPrefix + 'career/social/news/index.htm','secondaryUrlVariableField==article');
g_navNode_33_1_1=g_navNode_33_1.addNode('12635','\u6210\u957f\u5728\u534e\u4e3a',ssUrlPrefix + 'career/social/grow-with-huawei/index.htm','secondaryUrlVariableField==article');
g_navNode_33_1_2=g_navNode_33_1.addNode('12636','\u534e\u4e3a\u65b0\u7cbe\u5f69',ssUrlPrefix + 'career/social/brilliant-huawei/index.htm','secondaryUrlVariableField==article');
g_navNode_35=g_navNode_Root.addNode('13155','\u5168\u8054\u63a5\u4e16\u754c',ssUrlPrefix + 'better-connected-world/index.htm');
g_navNode_35_0=g_navNode_35.addNode('13152','\u4e92\u8054\u65b0\u6821\u56ed',ssUrlPrefix + 'better-connected-world/education/index.htm');
g_navNode_35_0_0=g_navNode_35_0.addNode('13156','\u884c\u4e1a\u5c55\u671b',ssUrlPrefix + 'better-connected-world/education/perspectives/index.htm','secondaryUrlVariableField==article');
g_navNode_35_0_1=g_navNode_35_0.addNode('11138','\u521b\u65b0\u5b9e\u8df5',ssUrlPrefix + 'better-connected-world/education/case-studies/index.htm','secondaryUrlVariableField==article');
g_navNode_36=g_navNode_Root.addNode('15772','terminations',ssUrlPrefix + 'terminations/index.htm');
