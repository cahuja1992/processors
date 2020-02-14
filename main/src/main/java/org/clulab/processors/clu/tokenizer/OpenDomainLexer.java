// Generated from main/src/main/java/org/clulab/processors/clu/tokenizer/OpenDomainLexer.g by ANTLR 4.6

  package org.clulab.processors.clu.tokenizer;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class OpenDomainLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PARENS=1, QUOTES=2, DATE=3, NUMBER=4, FRACTION=5, WORD=6, TWITTER_NAME=7, 
		TWITTER_HASHTAG=8, FILENAME=9, PROGRAMMING_LANGUAGES=10, FULLURL=11, LIKELYURL_WWW=12, 
		LIKELYURL_COM=13, EMAIL=14, SGML=15, HTML_CODE=16, SMILEY=17, LETTER_SMILEY=18, 
		EOS=19, WHITESPACE=20, ErrorCharacter=21;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"PARENS", "QUOTES", "DATE", "NUMBER", "FRACTION", "WORD", "TWITTER_NAME", 
		"TWITTER_HASHTAG", "FILENAME", "PROGRAMMING_LANGUAGES", "FULLURL", "LIKELYURL_WWW", 
		"LIKELYURL_COM", "EMAIL", "SGML", "HTML_CODE", "SMILEY", "LETTER_SMILEY", 
		"EOS", "WHITESPACE", "ErrorCharacter", "LOWER_CASE_LETTER", "UPPER_CASE_LETTER", 
		"SPLET", "LETTER", "DIGIT", "ALPHANUM", "NUM", "ONE_TO_TWO_DIGITS", "TWO_TO_FOUR_DIGITS", 
		"ONE_TO_FOUR_DIGITS", "PUNCTUATION", "FILENAME_EXT", "URL_BLOCK1", "URL_BLOCK2", 
		"URL_BLOCK3", "URL_END1", "URL_END2", "URL_END3", "EMAIL_USER", "EMAIL_DOMAIN"
	};

	private static final String[] _LITERAL_NAMES = {
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PARENS", "QUOTES", "DATE", "NUMBER", "FRACTION", "WORD", "TWITTER_NAME", 
		"TWITTER_HASHTAG", "FILENAME", "PROGRAMMING_LANGUAGES", "FULLURL", "LIKELYURL_WWW", 
		"LIKELYURL_COM", "EMAIL", "SGML", "HTML_CODE", "SMILEY", "LETTER_SMILEY", 
		"EOS", "WHITESPACE", "ErrorCharacter"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public OpenDomainLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "OpenDomainLexer.g"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 17:
			return LETTER_SMILEY_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean LETTER_SMILEY_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return ! Character.isLetterOrDigit(_input.LA(1));
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\27\u0265\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2t\n\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\5\3|\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\5\5\u0085\n\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\7\6\7\u008e\n\7\r\7\16\7\u008f\3\7\3\7\6\7\u0094\n"+
		"\7\r\7\16\7\u0095\7\7\u0098\n\7\f\7\16\7\u009b\13\7\3\b\3\b\3\b\3\b\5"+
		"\b\u00a1\n\b\3\b\3\b\3\b\3\b\7\b\u00a7\n\b\f\b\16\b\u00aa\13\b\3\t\3\t"+
		"\6\t\u00ae\n\t\r\t\16\t\u00af\3\n\6\n\u00b3\n\n\r\n\16\n\u00b4\3\n\3\n"+
		"\6\n\u00b9\n\n\r\n\16\n\u00ba\7\n\u00bd\n\n\f\n\16\n\u00c0\13\n\3\n\3"+
		"\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00cc\n\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\5\f\u00d4\n\f\3\f\3\f\3\f\3\f\3\f\6\f\u00db\n\f\r\f\16\f"+
		"\u00dc\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u00e7\n\r\r\r\16\r\u00e8\3"+
		"\r\3\r\6\r\u00ed\n\r\r\r\16\r\u00ee\3\r\3\r\5\r\u00f3\n\r\3\r\3\r\5\r"+
		"\u00f7\n\r\3\r\3\r\5\r\u00fb\n\r\3\r\3\r\5\r\u00ff\n\r\3\16\6\16\u0102"+
		"\n\16\r\16\16\16\u0103\3\16\3\16\6\16\u0108\n\16\r\16\16\16\u0109\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\5\16\u0120\n\16\3\16\3\16\6\16\u0124\n\16\r"+
		"\16\16\16\u0125\3\16\3\16\5\16\u012a\n\16\3\17\3\17\3\17\3\17\3\17\5\17"+
		"\u0131\n\17\3\17\3\17\3\17\3\17\3\17\7\17\u0138\n\17\f\17\16\17\u013b"+
		"\13\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0143\n\17\3\20\3\20\5\20\u0147"+
		"\n\20\3\20\6\20\u014a\n\20\r\20\16\20\u014b\3\20\3\20\3\21\3\21\3\21\5"+
		"\21\u0153\n\21\3\21\3\21\5\21\u0157\n\21\3\21\3\21\5\21\u015b\n\21\3\21"+
		"\3\21\5\21\u015f\n\21\3\21\3\21\3\22\5\22\u0164\n\22\3\22\3\22\5\22\u0168"+
		"\n\22\3\22\3\22\3\23\5\23\u016d\n\23\3\23\3\23\5\23\u0171\n\23\3\23\3"+
		"\23\3\23\3\24\3\24\7\24\u0178\n\24\f\24\16\24\u017b\13\24\3\25\6\25\u017e"+
		"\n\25\r\25\16\25\u017f\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5"+
		"\31\u0199\n\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u01a2\n\32\3\33"+
		"\5\33\u01a5\n\33\3\34\3\34\5\34\u01a9\n\34\3\35\6\35\u01ac\n\35\r\35\16"+
		"\35\u01ad\3\35\7\35\u01b1\n\35\f\35\16\35\u01b4\13\35\3\35\3\35\6\35\u01b8"+
		"\n\35\r\35\16\35\u01b9\6\35\u01bc\n\35\r\35\16\35\u01bd\5\35\u01c0\n\35"+
		"\3\36\3\36\5\36\u01c4\n\36\3\37\3\37\3\37\5\37\u01c9\n\37\3\37\5\37\u01cc"+
		"\n\37\3 \3 \5 \u01d0\n \3 \5 \u01d3\n \3 \5 \u01d6\n \3!\3!\3\"\3\"\3"+
		"\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\""+
		"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3"+
		"\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\""+
		"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3"+
		"\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\""+
		"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3"+
		"\"\3\"\3\"\3\"\3\"\5\"\u024a\n\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3"+
		"(\3)\5)\u0259\n)\3)\7)\u025c\n)\f)\16)\u025f\13)\3*\6*\u0262\n*\r*\16"+
		"*\u0263\2\2+\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\2/\2\61\2\63\2\65\2\67\2"+
		"9\2;\2=\2?\2A\2C\2E\2G\2I\2K\2M\2O\2Q\2S\2\3\2\36\4\2\u201a\u201b\u201e"+
		"\u201f\4\2//\61\61\4\2--//\4\2\61\61\u2046\u2046\7\2##))/\60AAaa\4\2E"+
		"Eee\4\2HHhh\t\2##*+..\60\60>>@A}\177\4\2>>@@\4\2<=??\6\2)),,//qq\6\2*"+
		"+BB]_}~\6\2FFQRffrr\t\2\13\17\"\"\u0087\u0087\u00a2\u00a2\u2002\u200c"+
		"\u202a\u202b\u3002\u3002\f\2CCGGKKQQWWccggkkqqww.\2\u00af\u00af\u0239"+
		"\u0251\u02c4\u02c7\u02d4\u02e1\u02e7\u037f\u0386\u0387\u03d1\u03d1\u03f8"+
		"\u03f8\u03fe\u0401\u0485\u0489\u04d1\u04d1\u04f8\u0501\u0512\u0527\u055c"+
		"\u0561\u0593\u05bf\u05c1\u05c1\u05c3\u05c4\u05c6\u05c7\u05c9\u05c9\u0617"+
		"\u061c\u063d\u0641\u064d\u0660\u0672\u0672\u06d8\u06f1\u06fc\u0701\u0711"+
		"\u0711\u0713\u0713\u0732\u0781\u07a8\u07b3\u07cc\u07f7\u07fc\u07fc\u0902"+
		"\u0905\u093e\u093e\u0940\u0950\u0953\u0957\u0964\u0965\u0983\u0985\u09be"+
		"\u09c6\u09c9\u09ca\u09cd\u09cf\u09d9\u09d9\u09e4\u09e5\u0a03\u0a05\u0a3e"+
		"\u0a3e\17\2\u0abe\u0ad1\u0b84\u0b84\u0bc0\u0bc4\u0bc8\u0bca\u0bcc\u0bcf"+
		"\u0c03\u0c05\u0c40\u0c58\u0d40\u0d46\u0d48\u0d4a\u0e32\u0e3c\u0e49\u0e50"+
		"\u0eb3\u0ebe\u0eca\u0ecf\4\2\62;\u07c2\u07cb\7\2..\60\60<<\u00af\u00af"+
		"\u066d\u066e\7\2##..\60\60==AA\n\2\13\f\16\17\"\"$$*+>>@@}\177\13\2\13"+
		"\f\16\17\"$*+..\60\60>>@A}\177\f\2\13\f\16\17\"$&&)+.\60>>@Aab}\177\n"+
		"\2\13\f\16\17\"$*+.\60>>@A}\177\n\2\13\f\16\17\"\"$$*+>>@@~~\5\2\62;C"+
		"\\c|\13\2\13\f\16\17\"\"$$*+>>@@}\177\u00a2\u00a2\r\2\13\f\16\17\"\"$"+
		"$*+..\60\60>>@@}\177\u00a2\u00a2\u02c7\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\3s\3\2\2\2\5{\3\2\2\2\7}\3\2\2\2\t\u0084\3\2\2\2\13"+
		"\u0088\3\2\2\2\r\u008d\3\2\2\2\17\u009c\3\2\2\2\21\u00ab\3\2\2\2\23\u00b2"+
		"\3\2\2\2\25\u00cb\3\2\2\2\27\u00cd\3\2\2\2\31\u00e0\3\2\2\2\33\u0107\3"+
		"\2\2\2\35\u0130\3\2\2\2\37\u0144\3\2\2\2!\u014f\3\2\2\2#\u0163\3\2\2\2"+
		"%\u016c\3\2\2\2\'\u0175\3\2\2\2)\u017d\3\2\2\2+\u0183\3\2\2\2-\u0185\3"+
		"\2\2\2/\u0187\3\2\2\2\61\u0189\3\2\2\2\63\u01a1\3\2\2\2\65\u01a4\3\2\2"+
		"\2\67\u01a8\3\2\2\29\u01bf\3\2\2\2;\u01c1\3\2\2\2=\u01c5\3\2\2\2?\u01cd"+
		"\3\2\2\2A\u01d7\3\2\2\2C\u0249\3\2\2\2E\u024b\3\2\2\2G\u024d\3\2\2\2I"+
		"\u024f\3\2\2\2K\u0251\3\2\2\2M\u0253\3\2\2\2O\u0255\3\2\2\2Q\u0258\3\2"+
		"\2\2S\u0261\3\2\2\2UV\7/\2\2VW\7N\2\2WX\7T\2\2XY\7D\2\2Yt\7/\2\2Z[\7/"+
		"\2\2[\\\7T\2\2\\]\7T\2\2]^\7D\2\2^t\7/\2\2_`\7/\2\2`a\7N\2\2ab\7E\2\2"+
		"bc\7D\2\2ct\7/\2\2de\7/\2\2ef\7T\2\2fg\7E\2\2gh\7D\2\2ht\7/\2\2ij\7/\2"+
		"\2jk\7N\2\2kl\7U\2\2lm\7D\2\2mt\7/\2\2no\7/\2\2op\7T\2\2pq\7U\2\2qr\7"+
		"D\2\2rt\7/\2\2sU\3\2\2\2sZ\3\2\2\2s_\3\2\2\2sd\3\2\2\2si\3\2\2\2sn\3\2"+
		"\2\2t\4\3\2\2\2u|\7$\2\2vw\7b\2\2w|\7b\2\2xy\7)\2\2y|\7)\2\2z|\t\2\2\2"+
		"{u\3\2\2\2{v\3\2\2\2{x\3\2\2\2{z\3\2\2\2|\6\3\2\2\2}~\5;\36\2~\177\t\3"+
		"\2\2\177\u0080\5;\36\2\u0080\u0081\t\3\2\2\u0081\u0082\5=\37\2\u0082\b"+
		"\3\2\2\2\u0083\u0085\t\4\2\2\u0084\u0083\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\u0086\3\2\2\2\u0086\u0087\59\35\2\u0087\n\3\2\2\2\u0088\u0089\5? \2\u0089"+
		"\u008a\t\5\2\2\u008a\u008b\5? \2\u008b\f\3\2\2\2\u008c\u008e\5\67\34\2"+
		"\u008d\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090"+
		"\3\2\2\2\u0090\u0099\3\2\2\2\u0091\u0093\t\6\2\2\u0092\u0094\5\67\34\2"+
		"\u0093\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096"+
		"\3\2\2\2\u0096\u0098\3\2\2\2\u0097\u0091\3\2\2\2\u0098\u009b\3\2\2\2\u0099"+
		"\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\16\3\2\2\2\u009b\u0099\3\2\2"+
		"\2\u009c\u00a0\7B\2\2\u009d\u00a1\5-\27\2\u009e\u00a1\5/\30\2\u009f\u00a1"+
		"\7a\2\2\u00a0\u009d\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u009f\3\2\2\2\u00a1"+
		"\u00a8\3\2\2\2\u00a2\u00a7\5-\27\2\u00a3\u00a7\5/\30\2\u00a4\u00a7\7a"+
		"\2\2\u00a5\u00a7\5\65\33\2\u00a6\u00a2\3\2\2\2\u00a6\u00a3\3\2\2\2\u00a6"+
		"\u00a4\3\2\2\2\u00a6\u00a5\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2"+
		"\2\2\u00a8\u00a9\3\2\2\2\u00a9\20\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00ad"+
		"\7%\2\2\u00ac\u00ae\5\63\32\2\u00ad\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2"+
		"\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\22\3\2\2\2\u00b1\u00b3"+
		"\5\67\34\2\u00b2\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b2\3\2\2\2"+
		"\u00b4\u00b5\3\2\2\2\u00b5\u00be\3\2\2\2\u00b6\u00b8\7\60\2\2\u00b7\u00b9"+
		"\5\67\34\2\u00b8\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00b8\3\2\2\2"+
		"\u00ba\u00bb\3\2\2\2\u00bb\u00bd\3\2\2\2\u00bc\u00b6\3\2\2\2\u00bd\u00c0"+
		"\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c1\3\2\2\2\u00c0"+
		"\u00be\3\2\2\2\u00c1\u00c2\7\60\2\2\u00c2\u00c3\5C\"\2\u00c3\24\3\2\2"+
		"\2\u00c4\u00c5\t\7\2\2\u00c5\u00c6\7-\2\2\u00c6\u00cc\7-\2\2\u00c7\u00c8"+
		"\t\7\2\2\u00c8\u00cc\7%\2\2\u00c9\u00ca\t\b\2\2\u00ca\u00cc\7%\2\2\u00cb"+
		"\u00c4\3\2\2\2\u00cb\u00c7\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cc\26\3\2\2"+
		"\2\u00cd\u00ce\7j\2\2\u00ce\u00cf\7v\2\2\u00cf\u00d0\7v\2\2\u00d0\u00d1"+
		"\7r\2\2\u00d1\u00d3\3\2\2\2\u00d2\u00d4\7u\2\2\u00d3\u00d2\3\2\2\2\u00d3"+
		"\u00d4\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\7<\2\2\u00d6\u00d7\7\61"+
		"\2\2\u00d7\u00d8\7\61\2\2\u00d8\u00da\3\2\2\2\u00d9\u00db\5E#\2\u00da"+
		"\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2"+
		"\2\2\u00dd\u00de\3\2\2\2\u00de\u00df\5K&\2\u00df\30\3\2\2\2\u00e0\u00e1"+
		"\7y\2\2\u00e1\u00e2\7y\2\2\u00e2\u00e3\7y\2\2\u00e3\u00e4\7\60\2\2\u00e4"+
		"\u00ec\3\2\2\2\u00e5\u00e7\5G$\2\u00e6\u00e5\3\2\2\2\u00e7\u00e8\3\2\2"+
		"\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00eb"+
		"\7\60\2\2\u00eb\u00ed\3\2\2\2\u00ec\u00e6\3\2\2\2\u00ed\u00ee\3\2\2\2"+
		"\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0\u00f3"+
		"\5-\27\2\u00f1\u00f3\5/\30\2\u00f2\u00f0\3\2\2\2\u00f2\u00f1\3\2\2\2\u00f3"+
		"\u00f6\3\2\2\2\u00f4\u00f7\5-\27\2\u00f5\u00f7\5/\30\2\u00f6\u00f4\3\2"+
		"\2\2\u00f6\u00f5\3\2\2\2\u00f7\u00fa\3\2\2\2\u00f8\u00fb\5-\27\2\u00f9"+
		"\u00fb\5/\30\2\u00fa\u00f8\3\2\2\2\u00fa\u00f9\3\2\2\2\u00fa\u00fb\3\2"+
		"\2\2\u00fb\u00fe\3\2\2\2\u00fc\u00ff\5-\27\2\u00fd\u00ff\5/\30\2\u00fe"+
		"\u00fc\3\2\2\2\u00fe\u00fd\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\32\3\2\2"+
		"\2\u0100\u0102\5I%\2\u0101\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0101"+
		"\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0106\7\60\2\2"+
		"\u0106\u0108\3\2\2\2\u0107\u0101\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u0107"+
		"\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u011f\3\2\2\2\u010b\u010c\7e\2\2\u010c"+
		"\u010d\7q\2\2\u010d\u0120\7o\2\2\u010e\u010f\7p\2\2\u010f\u0110\7g\2\2"+
		"\u0110\u0120\7v\2\2\u0111\u0112\7q\2\2\u0112\u0113\7t\2\2\u0113\u0120"+
		"\7i\2\2\u0114\u0115\7g\2\2\u0115\u0116\7f\2\2\u0116\u0120\7w\2\2\u0117"+
		"\u0118\7p\2\2\u0118\u0119\7c\2\2\u0119\u011a\7o\2\2\u011a\u0120\7g\2\2"+
		"\u011b\u011c\7k\2\2\u011c\u011d\7p\2\2\u011d\u011e\7h\2\2\u011e\u0120"+
		"\7q\2\2\u011f\u010b\3\2\2\2\u011f\u010e\3\2\2\2\u011f\u0111\3\2\2\2\u011f"+
		"\u0114\3\2\2\2\u011f\u0117\3\2\2\2\u011f\u011b\3\2\2\2\u0120\u0129\3\2"+
		"\2\2\u0121\u0123\7\61\2\2\u0122\u0124\5M\'\2\u0123\u0122\3\2\2\2\u0124"+
		"\u0125\3\2\2\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0127\3\2"+
		"\2\2\u0127\u0128\5O(\2\u0128\u012a\3\2\2\2\u0129\u0121\3\2\2\2\u0129\u012a"+
		"\3\2\2\2\u012a\34\3\2\2\2\u012b\u012c\7(\2\2\u012c\u012d\7n\2\2\u012d"+
		"\u012e\7v\2\2\u012e\u0131\7=\2\2\u012f\u0131\7>\2\2\u0130\u012b\3\2\2"+
		"\2\u0130\u012f\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0133"+
		"\5Q)\2\u0133\u0139\7B\2\2\u0134\u0135\5S*\2\u0135\u0136\7\60\2\2\u0136"+
		"\u0138\3\2\2\2\u0137\u0134\3\2\2\2\u0138\u013b\3\2\2\2\u0139\u0137\3\2"+
		"\2\2\u0139\u013a\3\2\2\2\u013a\u013c\3\2\2\2\u013b\u0139\3\2\2\2\u013c"+
		"\u0142\5S*\2\u013d\u013e\7(\2\2\u013e\u013f\7i\2\2\u013f\u0140\7v\2\2"+
		"\u0140\u0143\7=\2\2\u0141\u0143\7@\2\2\u0142\u013d\3\2\2\2\u0142\u0141"+
		"\3\2\2\2\u0142\u0143\3\2\2\2\u0143\36\3\2\2\2\u0144\u0146\7>\2\2\u0145"+
		"\u0147\7\61\2\2\u0146\u0145\3\2\2\2\u0146\u0147\3\2\2\2\u0147\u0149\3"+
		"\2\2\2\u0148\u014a\n\t\2\2\u0149\u0148\3\2\2\2\u014a\u014b\3\2\2\2\u014b"+
		"\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u014e\7@"+
		"\2\2\u014e \3\2\2\2\u014f\u0152\7(\2\2\u0150\u0153\5-\27\2\u0151\u0153"+
		"\5/\30\2\u0152\u0150\3\2\2\2\u0152\u0151\3\2\2\2\u0153\u0156\3\2\2\2\u0154"+
		"\u0157\5-\27\2\u0155\u0157\5/\30\2\u0156\u0154\3\2\2\2\u0156\u0155\3\2"+
		"\2\2\u0157\u015a\3\2\2\2\u0158\u015b\5-\27\2\u0159\u015b\5/\30\2\u015a"+
		"\u0158\3\2\2\2\u015a\u0159\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u015e\3\2"+
		"\2\2\u015c\u015f\5-\27\2\u015d\u015f\5/\30\2\u015e\u015c\3\2\2\2\u015e"+
		"\u015d\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u0161\7="+
		"\2\2\u0161\"\3\2\2\2\u0162\u0164\t\n\2\2\u0163\u0162\3\2\2\2\u0163\u0164"+
		"\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u0167\t\13\2\2\u0166\u0168\t\f\2\2"+
		"\u0167\u0166\3\2\2\2\u0167\u0168\3\2\2\2\u0168\u0169\3\2\2\2\u0169\u016a"+
		"\t\r\2\2\u016a$\3\2\2\2\u016b\u016d\t\n\2\2\u016c\u016b\3\2\2\2\u016c"+
		"\u016d\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u0170\t\13\2\2\u016f\u0171\t"+
		"\f\2\2\u0170\u016f\3\2\2\2\u0170\u0171\3\2\2\2\u0171\u0172\3\2\2\2\u0172"+
		"\u0173\t\16\2\2\u0173\u0174\6\23\2\2\u0174&\3\2\2\2\u0175\u0179\5A!\2"+
		"\u0176\u0178\5A!\2\u0177\u0176\3\2\2\2\u0178\u017b\3\2\2\2\u0179\u0177"+
		"\3\2\2\2\u0179\u017a\3\2\2\2\u017a(\3\2\2\2\u017b\u0179\3\2\2\2\u017c"+
		"\u017e\t\17\2\2\u017d\u017c\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u017d\3"+
		"\2\2\2\u017f\u0180\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u0182\b\25\2\2\u0182"+
		"*\3\2\2\2\u0183\u0184\13\2\2\2\u0184,\3\2\2\2\u0185\u0186\4c|\2\u0186"+
		".\3\2\2\2\u0187\u0188\4C\\\2\u0188\60\3\2\2\2\u0189\u018a\7(\2\2\u018a"+
		"\u0198\t\20\2\2\u018b\u018c\7c\2\2\u018c\u018d\7e\2\2\u018d\u018e\7w\2"+
		"\2\u018e\u018f\7v\2\2\u018f\u0199\7g\2\2\u0190\u0191\7i\2\2\u0191\u0192"+
		"\7t\2\2\u0192\u0193\7c\2\2\u0193\u0194\7x\2\2\u0194\u0199\7g\2\2\u0195"+
		"\u0196\7w\2\2\u0196\u0197\7o\2\2\u0197\u0199\7n\2\2\u0198\u018b\3\2\2"+
		"\2\u0198\u0190\3\2\2\2\u0198\u0195\3\2\2\2\u0199\62\3\2\2\2\u019a\u01a2"+
		"\5-\27\2\u019b\u01a2\5/\30\2\u019c\u01a2\5\61\31\2\u019d\u01a2\t\21\2"+
		"\2\u019e\u019f\4\u0a40\u0a51\2\u019f\u01a2\4\u0a83\u0a85\2\u01a0\u01a2"+
		"\t\22\2\2\u01a1\u019a\3\2\2\2\u01a1\u019b\3\2\2\2\u01a1\u019c\3\2\2\2"+
		"\u01a1\u019d\3\2\2\2\u01a1\u019e\3\2\2\2\u01a1\u01a0\3\2\2\2\u01a2\64"+
		"\3\2\2\2\u01a3\u01a5\t\23\2\2\u01a4\u01a3\3\2\2\2\u01a5\66\3\2\2\2\u01a6"+
		"\u01a9\5\63\32\2\u01a7\u01a9\5\65\33\2\u01a8\u01a6\3\2\2\2\u01a8\u01a7"+
		"\3\2\2\2\u01a98\3\2\2\2\u01aa\u01ac\5\65\33\2\u01ab\u01aa\3\2\2\2\u01ac"+
		"\u01ad\3\2\2\2\u01ad\u01ab\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae\u01c0\3\2"+
		"\2\2\u01af\u01b1\5\65\33\2\u01b0\u01af\3\2\2\2\u01b1\u01b4\3\2\2\2\u01b2"+
		"\u01b0\3\2\2\2\u01b2\u01b3\3\2\2\2\u01b3\u01bb\3\2\2\2\u01b4\u01b2\3\2"+
		"\2\2\u01b5\u01b7\t\24\2\2\u01b6\u01b8\5\65\33\2\u01b7\u01b6\3\2\2\2\u01b8"+
		"\u01b9\3\2\2\2\u01b9\u01b7\3\2\2\2\u01b9\u01ba\3\2\2\2\u01ba\u01bc\3\2"+
		"\2\2\u01bb\u01b5\3\2\2\2\u01bc\u01bd\3\2\2\2\u01bd\u01bb\3\2\2\2\u01bd"+
		"\u01be\3\2\2\2\u01be\u01c0\3\2\2\2\u01bf\u01ab\3\2\2\2\u01bf\u01b2\3\2"+
		"\2\2\u01c0:\3\2\2\2\u01c1\u01c3\5\65\33\2\u01c2\u01c4\5\65\33\2\u01c3"+
		"\u01c2\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4<\3\2\2\2\u01c5\u01c6\5\65\33"+
		"\2\u01c6\u01c8\5\65\33\2\u01c7\u01c9\5\65\33\2\u01c8\u01c7\3\2\2\2\u01c8"+
		"\u01c9\3\2\2\2\u01c9\u01cb\3\2\2\2\u01ca\u01cc\5\65\33\2\u01cb\u01ca\3"+
		"\2\2\2\u01cb\u01cc\3\2\2\2\u01cc>\3\2\2\2\u01cd\u01cf\5\65\33\2\u01ce"+
		"\u01d0\5\65\33\2\u01cf\u01ce\3\2\2\2\u01cf\u01d0\3\2\2\2\u01d0\u01d2\3"+
		"\2\2\2\u01d1\u01d3\5\65\33\2\u01d2\u01d1\3\2\2\2\u01d2\u01d3\3\2\2\2\u01d3"+
		"\u01d5\3\2\2\2\u01d4\u01d6\5\65\33\2\u01d5\u01d4\3\2\2\2\u01d5\u01d6\3"+
		"\2\2\2\u01d6@\3\2\2\2\u01d7\u01d8\t\25\2\2\u01d8B\3\2\2\2\u01d9\u01da"+
		"\7d\2\2\u01da\u01db\7c\2\2\u01db\u024a\7v\2\2\u01dc\u01dd\7d\2\2\u01dd"+
		"\u01de\7o\2\2\u01de\u024a\7r\2\2\u01df\u024a\7e\2\2\u01e0\u01e1\7e\2\2"+
		"\u01e1\u01e2\7n\2\2\u01e2\u01e3\7c\2\2\u01e3\u01e4\7u\2\2\u01e4\u024a"+
		"\7u\2\2\u01e5\u01e6\7e\2\2\u01e6\u01e7\7i\2\2\u01e7\u024a\7k\2\2\u01e8"+
		"\u01e9\7e\2\2\u01e9\u01ea\7r\2\2\u01ea\u024a\7r\2\2\u01eb\u01ec\7f\2\2"+
		"\u01ec\u01ed\7n\2\2\u01ed\u024a\7n\2\2\u01ee\u01ef\7f\2\2\u01ef\u01f0"+
		"\7q\2\2\u01f0\u024a\7e\2\2\u01f1\u01f2\7f\2\2\u01f2\u01f3\7q\2\2\u01f3"+
		"\u01f4\7e\2\2\u01f4\u024a\7z\2\2\u01f5\u01f6\7g\2\2\u01f6\u01f7\7z\2\2"+
		"\u01f7\u024a\7g\2\2\u01f8\u01f9\7i\2\2\u01f9\u01fa\7k\2\2\u01fa\u024a"+
		"\7h\2\2\u01fb\u01fc\7i\2\2\u01fc\u024a\7|\2\2\u01fd\u024a\7j\2\2\u01fe"+
		"\u01ff\7j\2\2\u01ff\u0200\7v\2\2\u0200\u024a\7o\2\2\u0201\u0202\7j\2\2"+
		"\u0202\u0203\7v\2\2\u0203\u0204\7o\2\2\u0204\u024a\7n\2\2\u0205\u0206"+
		"\7l\2\2\u0206\u0207\7c\2\2\u0207\u024a\7t\2\2\u0208\u0209\7l\2\2\u0209"+
		"\u020a\7c\2\2\u020a\u020b\7x\2\2\u020b\u024a\7c\2\2\u020c\u020d\7l\2\2"+
		"\u020d\u020e\7r\2\2\u020e\u020f\7g\2\2\u020f\u024a\7i\2\2\u0210\u0211"+
		"\7l\2\2\u0211\u0212\7r\2\2\u0212\u024a\7i\2\2\u0213\u0214\7o\2\2\u0214"+
		"\u0215\7q\2\2\u0215\u024a\7x\2\2\u0216\u0217\7o\2\2\u0217\u0218\7r\2\2"+
		"\u0218\u024a\7\65\2\2\u0219\u021a\7r\2\2\u021a\u021b\7f\2\2\u021b\u024a"+
		"\7h\2\2\u021c\u021d\7r\2\2\u021d\u021e\7j\2\2\u021e\u024a\7r\2\2\u021f"+
		"\u0220\7r\2\2\u0220\u024a\7n\2\2\u0221\u0222\7r\2\2\u0222\u0223\7p\2\2"+
		"\u0223\u024a\7i\2\2\u0224\u0225\7r\2\2\u0225\u0226\7r\2\2\u0226\u024a"+
		"\7v\2\2\u0227\u0228\7r\2\2\u0228\u0229\7r\2\2\u0229\u022a\7v\2\2\u022a"+
		"\u024a\7z\2\2\u022b\u022c\7r\2\2\u022c\u024a\7u\2\2\u022d\u022e\7r\2\2"+
		"\u022e\u024a\7{\2\2\u022f\u0230\7u\2\2\u0230\u0231\7e\2\2\u0231\u0232"+
		"\7c\2\2\u0232\u0233\7n\2\2\u0233\u024a\7c\2\2\u0234\u0235\7u\2\2\u0235"+
		"\u0236\7s\2\2\u0236\u024a\7n\2\2\u0237\u0238\7v\2\2\u0238\u0239\7c\2\2"+
		"\u0239\u024a\7t\2\2\u023a\u023b\7v\2\2\u023b\u023c\7i\2\2\u023c\u024a"+
		"\7|\2\2\u023d\u023e\7v\2\2\u023e\u023f\7z\2\2\u023f\u024a\7v\2\2\u0240"+
		"\u0241\7y\2\2\u0241\u0242\7c\2\2\u0242\u024a\7x\2\2\u0243\u0244\7z\2\2"+
		"\u0244\u0245\7o\2\2\u0245\u024a\7n\2\2\u0246\u0247\7|\2\2\u0247\u0248"+
		"\7k\2\2\u0248\u024a\7r\2\2\u0249\u01d9\3\2\2\2\u0249\u01dc\3\2\2\2\u0249"+
		"\u01df\3\2\2\2\u0249\u01e0\3\2\2\2\u0249\u01e5\3\2\2\2\u0249\u01e8\3\2"+
		"\2\2\u0249\u01eb\3\2\2\2\u0249\u01ee\3\2\2\2\u0249\u01f1\3\2\2\2\u0249"+
		"\u01f5\3\2\2\2\u0249\u01f8\3\2\2\2\u0249\u01fb\3\2\2\2\u0249\u01fd\3\2"+
		"\2\2\u0249\u01fe\3\2\2\2\u0249\u0201\3\2\2\2\u0249\u0205\3\2\2\2\u0249"+
		"\u0208\3\2\2\2\u0249\u020c\3\2\2\2\u0249\u0210\3\2\2\2\u0249\u0213\3\2"+
		"\2\2\u0249\u0216\3\2\2\2\u0249\u0219\3\2\2\2\u0249\u021c\3\2\2\2\u0249"+
		"\u021f\3\2\2\2\u0249\u0221\3\2\2\2\u0249\u0224\3\2\2\2\u0249\u0227\3\2"+
		"\2\2\u0249\u022b\3\2\2\2\u0249\u022d\3\2\2\2\u0249\u022f\3\2\2\2\u0249"+
		"\u0234\3\2\2\2\u0249\u0237\3\2\2\2\u0249\u023a\3\2\2\2\u0249\u023d\3\2"+
		"\2\2\u0249\u0240\3\2\2\2\u0249\u0243\3\2\2\2\u0249\u0246\3\2\2\2\u024a"+
		"D\3\2\2\2\u024b\u024c\n\26\2\2\u024cF\3\2\2\2\u024d\u024e\n\27\2\2\u024e"+
		"H\3\2\2\2\u024f\u0250\n\30\2\2\u0250J\3\2\2\2\u0251\u0252\n\31\2\2\u0252"+
		"L\3\2\2\2\u0253\u0254\n\32\2\2\u0254N\3\2\2\2\u0255\u0256\n\31\2\2\u0256"+
		"P\3\2\2\2\u0257\u0259\t\33\2\2\u0258\u0257\3\2\2\2\u0259\u025d\3\2\2\2"+
		"\u025a\u025c\n\34\2\2\u025b\u025a\3\2\2\2\u025c\u025f\3\2\2\2\u025d\u025b"+
		"\3\2\2\2\u025d\u025e\3\2\2\2\u025eR\3\2\2\2\u025f\u025d\3\2\2\2\u0260"+
		"\u0262\n\35\2\2\u0261\u0260\3\2\2\2\u0262\u0263\3\2\2\2\u0263\u0261\3"+
		"\2\2\2\u0263\u0264\3\2\2\2\u0264T\3\2\2\2A\2s{\u0084\u008f\u0095\u0099"+
		"\u00a0\u00a6\u00a8\u00af\u00b4\u00ba\u00be\u00cb\u00d3\u00dc\u00e8\u00ee"+
		"\u00f2\u00f6\u00fa\u00fe\u0103\u0109\u011f\u0125\u0129\u0130\u0139\u0142"+
		"\u0146\u014b\u0152\u0156\u015a\u015e\u0163\u0167\u016c\u0170\u0179\u017d"+
		"\u017f\u0198\u01a1\u01a4\u01a8\u01ad\u01b2\u01b9\u01bd\u01bf\u01c3\u01c8"+
		"\u01cb\u01cf\u01d2\u01d5\u0249\u0258\u025d\u0263\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}