package com.alisovenko.jdk.regexp;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * alisovenko 30.01.13
 */
public class RegexpTest {
	static final AtomicInteger cnt = new AtomicInteger(0);
	@Test
	public void testBoundary() {
		// word boundary (positions between words)
		// o
		find(".\\b", "Hello Bob", "before word");
		// H
		find("\\b.", "Hello Bob", "after word");
		// Some ~ word ~ and ~ sentence ~ Hey ~
		find("\\b[\\w\\.]+\\b", "Some word and sentence.Hey", "With dot");
		find("\\b[\\w\\.]+\\b", "Some word and sentence-Hey", "With dot");

		// H (cursor between H and e)
		find(".\\B", "Hello Bob", "inside word");
		// e (cursor between H and e)
		find("\\B.", "Hello Bob", "next inside word");
	}


	@Test
	public void testAddresses() {
		find(".*?(\\b([а-я]{3,40}\\-?\\.-?[а-я]{3,40}){1,3}\\b).*?", "бол-морская малая 6", "address");
		find(".*?(\\b([а-я]{3,40}\\-?\\.-?[а-я]{3,40})\\b).*?", "бол-морская малая 6", "address");
		find(".*?(\\b([а-я]{3,40}[а-я]{3,40})\\b).*?", "бол-морская малая 6", "address");
		find(".*?(\\b([а-я]{3,40}[а-я]{3,40}){1,3}\\b).*?", "бол-морская малая 6", "address");
		find("(\\b([а-я]{3,40}\\-?[а-я]{3,40}){1,3}\\b)", "бол-морская малая 6", "address");
		find("(\\b[а-я]{3,40}\\-?[а-я]{0,40}\\b)", "бол-морская малая 6", "address");
		find("(\\b([а-я]{3,40}[\\-\\.]?[а-я]{0,40}[\\-\\.]?){1,3}\\b)", "бол-морская.посадская малая 6", "address");
		find("(\\b([а-я]{3,40}[\\-\\.]?[а-я]{0,40}[\\-\\.]?){1,3}\\b)", "бол-.морская малая 6", "address");
		find("(\\b([а-я]{3,40}[\\-\\.]?[а-я]{0,40}[\\-\\.]?){1,3}\\b)", ".бол-.морская малая 6", "address");
		find("[а-яА-Я]{0,2}\\s(?:[а-яА-Я]{3,}\\s)", "М Золки 6", "address");
		//		find("java.util.regex.Matcher[pattern=(?<![А-Яа-я\\w]|/ ?(?:[а-я]{1,10}[\\. ]? ?)?(?:[А-Я][А-Яа-я]{1,15} )?)(?:\\s*((?:\\s*((?:\\s*((?<!\\d)\\d{1,2})\\s*)(?:\\-?(?:\\s*(й|я|ая|ий|го|ого|ой|ый|ти|летия|лет)\\s*))?)\\s*)?(?:\\s?[а-яА-Я]){0,2}(?:\\s*((?:\\s?[а-я]{3,}[\\-\\.]?[а-я]*[\\-\\.]?\\s?))\\s*){1,3}(?:\\s*((?:\\s*((?<!\\d)\\d{1,2})\\s*)\\-?(?:\\s*(й|я|ая|ий|го|ого|ой|ый|ти|летия|лет)\\s*))\\s*)?|(?:\\s*((?:\\s*((?<!\\d)\\d{1,2})\\s*)(?:\\-?(?:\\s*(й|я|ая|ий|го|ого|ой|ый|ти|летия|лет)\\s*))?)\\s*)(?:\\s*([Лл]иния|[Лл]ин.?|[Лл]-я|[Мм]кр.?|[Мм]-н|[Мм]р-н|[Мм]икрорайон|марта|октября|января|февраля|ноября)\\s*)|(?:\\s*([Лл]иния|[Лл]ин.?|[Лл]-я|[Мм]кр.?|[Мм]-н|[Мм]р-н|[Мм]икрорайон|марта|октября|января|февраля|ноября)\\s*)(?:\\s*((?:\\s*((?<!\\d)\\d{1,2})\\s*)\\-?(?:\\s*(й|я|ая|ий|го|ого|ой|ый|ти|летия|лет)\\s*))\\s*))\\s*)(?:\\s*((?:\\s*([Пп]арк|[Тт]упик|[Уу]лица|[Лл]иния|[Шш]оссе|[Дд]орога|[Сс]ад|[Бб]ульвар|[Пп]лощадь|[Пп]роспект|[Пп]ереулок|[Пп]роезд|[Тт]ракт|[Аа]ллея|(?:[Тт]уп|[Пп]л|[Пп]росп|[Лл]ин|[Лл]\\-я|[Бб]ул|[Пп]ер|[Пп]р|[Аа]л|[Уу]л|[Вв]ул|[Шш]|[Пп]р\\-д|[Пп]р\\-т|[Пп]р\\-кт|[Бб]\\-р)\\.?|(?:[Нн]абережная|[Нн]аб\\.?)(?:\\s+[Рр]еки)?)\\s*)?(?:\\s*([а-я]*|В\\.?О\\.?|П\\.?С\\.?)\\s*)(?:\\s*(\\((?:(?:[А-Яа-я]+) ){1,3}\\))\\s*)?)\\s*),?(?:\\s*((?:(?:\\s*(\\d{1,3}(?:\\-?й)?)\\s*)(?:\\s*(км\\.?)\\s*),?)?(?:(?:\\s*(\\d{1,3}(?:\\-?й)?)\\s*)(?:\\s*(км\\.?)\\s*)|(?:\\s*([Дд]ом|[Дд]\\.?)\\s*)?(?:\\s*(\\d{1,3})\\s*)(?:/?(?:\\s*((?:\\-?(?:[А-Иа-иA-Ea-e]|(?:\\s*(['\"‘’‛“”‟′″‴‵‶‷])\\s*)[А-Иа-иA-Ea-e](?:\\s*(['\"‘’‛“”‟′″‴‵‶‷])\\s*)|(?<=[/\\-])\\d{1,3}))?(?:,?\\s*(?:\\s*((?:[Лл]итера|[Лл]итер\\.?|[Лл]ит\\.?)\\s+(?:\\s*(['\"‘’‛“”‟′″‴‵‶‷])\\s*)?(?:[А-Яа-яA-Ea-e])(?:\\s*(['\"‘’‛“”‟′″‴‵‶‷])\\s*)?)\\s*))?)\\s*))?|(?:\\s*(владение|влад\\.?|вл\\.?)\\s*)(?:\\s*(\\d{1,3}(?:\\s*[/\\-]?\\s*[А-Иа-и]|\\s*[/\\-]\\s*\\d{1,3})?)\\s*)|(?:\\s*(строение|стр\\.?)\\s*)(?:\\s*(\\d{1,3}(?:\\s*[/\\-]?\\s*[А-Иа-и]|\\s*[/\\-]\\s*\\d{1,3})?)\\s*))(?:,?(?:\\s*(корпус|корп\\.?|[Кк]\\.?)\\s*)(?:\\s*(\\d|[А-Ла-л])\\s*))?(?:,?(?:\\s*(строение|стр\\.?)\\s*)(?:\\s*(\\d{1,3}(?:\\s*[/\\-]?\\s*[А-Иа-и]|\\s*[/\\-]\\s*\\d{1,3})?)\\s*))?(?:,?(?:\\s*((?:\\s*((?:\\s*(ТЦ|БЦ|ТК|ТРЦ)\\s*)(?:['\"](?:[А-Я][А-Яа-яё]+\\s*)(?:[А-Яа-яё]+\\s*)*['\"]|(?:[А-Я][А-Яа-яё]+\\s*)+))\\s*)?(?:(?:,|\\s)(?:\\s*((?:\\s*(секция|этаж|офис|зал|(?:сек|эт|пав|оф|каб|кв|к)\\.?)\\s*)№?\\s*\\d{1,3}|\\d{1,3}(?:\\s*(секция|этаж|офис|зал|(?:сек|эт|пав|оф|каб|кв|к)\\.?)\\s*))\\s*))*(?=$|[^А-Яа-я0-9]))\\s*))?)\\s*)(?![А-Яа-я\\w]) region=0,39 lastmatch=]",
		//				"", "");
		find("\\s?(?:[а-яА-Я]){0,2}\\s(?:\\s?[а-яА-Я]{3,}[\\-\\.]?[а-яА-Я]*[\\-\\.]?\\s?)", "улица Ма М Морская, 5",
				"address");
		find("(?:\\s?[а-яА-Я]{1,50}+[\\-\\.]?[а-яА-Я]{0,50}+[\\-\\.]?\\s?){1,5}?", "улица Ма М Морская, 5", "address");
		find("(?:\\s*((?:\\s*((?:\\s*((?<!\\d)\\d{1,2})\\s*)(?:\\-?(?:\\s*(й|я|ая|ий|го|ого|ой|ый|ти|летия|лет)\\s*))?)\\s*)?(?:\\s*((?:\\s?[а-яА-Я]{1,50}?[\\-\\.]?[а-яА-Я]{0,50}[\\-\\.]?\\s?))\\s*){1,5}(?:\\s*((?:\\s*((?<!\\d)\\d{1,2})\\s*)\\-?(?:\\s*(й|я|ая|ий|го|ого|ой|ый|ти|летия|лет)\\s*))\\s*)?|(?:\\s*((?:\\s*((?<!\\d)\\d{1,2})\\s*)(?:\\-?(?:\\s*(й|я|ая|ий|го|ого|ой|ый|ти|летия|лет)\\s*))?)\\s*)(?:\\s*([Лл]иния|[Лл]ин.?|[Лл]-я|[Мм]кр.?|[Мм]-н|[Мм]р-н|[Мм]икрорайон|марта|октября|января|февраля|ноября)\\s*)|(?:\\s*([Лл]иния|[Лл]ин.?|[Лл]-я|[Мм]кр.?|[Мм]-н|[Мм]р-н|[Мм]икрорайон|марта|октября|января|февраля|ноября)\\s*)(?:\\s*((?:\\s*((?<!\\d)\\d{1,2})\\s*)\\-?(?:\\s*(й|я|ая|ий|го|ого|ой|ый|ти|летия|лет)\\s*))\\s*))\\s*)(?:\\s*((?:\\s*([Пп]арк|[Тт]упик|[Уу]лица|[Лл]иния|[Шш]оссе|[Дд]орога|[Сс]ад|[Бб]ульвар|[Пп]лощадь|[Пп]роспект|[Пп]ереулок|[Пп]роезд|[Тт]ракт|[Аа]ллея|(?:[Тт]уп|[Пп]л|[Пп]росп|[Лл]ин|[Лл]\\-я|[Бб]ул|[Пп]ер|[Пп]р|[Аа]л|[Уу]л|[Вв]ул|[Шш]|[Пп]р\\-д|[Пп]р\\-т|[Пп]р\\-кт|[Бб]\\-р)\\.?|(?:[Нн]абережная|[Нн]аб\\.?)(?:\\s+[Рр]еки)?)\\s*)?(?:\\s*([а-я]*|В\\.?О\\.?|П\\.?С\\.?)\\s*)(?:\\s*(\\((?:(?:[А-Яа-я]+) ){1,3}\\))\\s*)?)\\s*),?(?:\\s*((?:(?:\\s*(\\d{1,3}(?:\\-?й)?)\\s*)(?:\\s*(км\\.?)\\s*),?)?(?:(?:\\s*(\\d{1,3}(?:\\-?й)?)\\s*)(?:\\s*(км\\.?)\\s*)|(?:\\s*([Дд]ом|[Дд]\\.?)\\s*)?(?:\\s*(\\d{1,3})\\s*)(?:/?(?:\\s*((?:\\-?(?:[А-Иа-иA-Ea-e]|(?:\\s*(['\"‘’‛“”‟′″‴‵‶‷])\\s*)[А-Иа-иA-Ea-e](?:\\s*(['\"‘’‛“”‟′″‴‵‶‷])\\s*)|(?<=[/\\-])\\d{1,3}))?(?:,?\\s*(?:\\s*((?:[Лл]итера|[Лл]итер\\.?|[Лл]ит\\.?)\\s+(?:\\s*(['\"‘’‛“”‟′″‴‵‶‷])\\s*)?(?:[А-Яа-яA-Ea-e])(?:\\s*(['\"‘’‛“”‟′″‴‵‶‷])\\s*)?)\\s*))?)\\s*))?|(?:\\s*(владение|влад\\.?|вл\\.?)\\s*)(?:\\s*(\\d{1,3}(?:\\s*[/\\-]?\\s*[А-Иа-и]|\\s*[/\\-]\\s*\\d{1,3})?)\\s*)|(?:\\s*(строение|стр\\.?)\\s*)(?:\\s*(\\d{1,3}(?:\\s*[/\\-]?\\s*[А-Иа-и]|\\s*[/\\-]\\s*\\d{1,3})?)\\s*))(?:,?(?:\\s*(корпус|корп\\.?|[Кк]\\.?)\\s*)(?:\\s*(\\d|[А-Ла-л])\\s*))?(?:,?(?:\\s*(строение|стр\\.?)\\s*)(?:\\s*(\\d{1,3}(?:\\s*[/\\-]?\\s*[А-Иа-и]|\\s*[/\\-]\\s*\\d{1,3})?)\\s*))?(?:,?(?:\\s*((?:\\s*((?:\\s*(ТЦ|БЦ|ТК|ТРЦ)\\s*)(?:['\"](?:[А-Я][А-Яа-яё]+\\s*)(?:[А-Яа-яё]+\\s*)*['\"]|(?:[А-Я][А-Яа-яё]+\\s*)+))\\s*)?(?:(?:,|\\s)(?:\\s*((?:\\s*(секция|этаж|офис|зал|(?:сек|эт|пав|оф|каб|кв|к)\\.?)\\s*)№?\\s*\\d{1,3}|\\d{1,3}(?:\\s*(секция|этаж|офис|зал|(?:сек|эт|пав|оф|каб|кв|к)\\.?)\\s*))\\s*))*(?=$|[^А-Яа-я0-9]))\\s*))?)\\s*)(?![А-Яа-я\\w])",
				"Санкт-Петербург, улица Б M Морская, 5", "address");
	}

	@Test
	public void testOthers() {
		find(".*?контакт(ы)?.{0,10}", "Контакты", null);
		findNamed("http://(?<dom>(www.)?([\\w\\d]{1,25}\\.){0,2})[\\w\\d]{1,25}\\.\\w{1,5}",
				"http://ekb.onlinetrade.ru/shops/fotopark_cankt_peterburg_v_trts_sitimoll_m._pionerskaya-s25.html",
				"dom");
		find("(?<=http://)((www.)?([\\w\\d]{1,25}\\.){0,2})(?=[\\w\\d]{1,25}\\.\\w{1,5}.*)",
				"http://www.ekb.onlinetrade.ru/shops/fotopark_cankt_peterburg_v_trts_sitimoll_m._pionerskaya-s25.html",
				"dom");
		find("(?<=http://)((www.)?([\\w\\d]{1,25}\\.){0,2})(?=[\\w\\d]{1,25}\\.\\w{1,5}.*)",
				"http://www.onlinetrade.ru/shops/fotopark_cankt_peterburg_v_trts_sitimoll_m._pionerskaya-s25.html",
				"dom");
		find("(?<=http://)((www.)?([\\w\\d-]{1,25}\\.){0,2})(?=[\\w\\d-]{1,25}\\.\\w{1,5}.*)", "http://www.c-capital.ru", "dom");
		find("(?<=http://)((www.)?([\\w\\d-]{1,25}\\.){0,2})(?=[\\w\\d-]{1,25}\\.\\w{1,5}.*)(/.*)", "http://engels.dns-shop.ru/shop/syktyvkar/na+oktyabr`skom+prospekte", "dom");
		String url ="http://engels.dns-shop.ru/shop/syktyvkar/na+oktyabr`skom+prospekte";
		url = url.substring(0, url.indexOf("/", 8));
		System.out.println(url);
		String s = "http://www.c-capital.ru";
		String p = "www.c-capital.ru";
		s = s.replaceFirst("(?<=http://)www\\.", "");
		p = p.replaceFirst("(?<=http://)www\\.", "");
		System.out.println(s);
		System.out.println(p);
	}

	@Test
	public void testExclude() {
		find("(?<=<).*?(?=>)", "<someTag> hi </someTag>", "Excluding tags brackets");
		find("^(?!пн-сб.*)[а-я\\s\\-\\d]+", "21018, г. Винница, пл. Гагарина, 2", "Checking for exclusion");
		find("somefile_(?!16\\.txt)\\d*?\\.txt", "somefile_16.txt somefile_17.txt", "Checking for exclusion 2");
		find("John (?!Smith)[A-Z]\\w+",
				"I think that John Smith is a fictional character. His real name might be John Jackson, John Westling, or John Holmes for all we know.",
				"Checking for exclusion 3");
		find("(?<!<noscript>\\s{0,4})<meta http-equiv=\"?refresh\"? content=\"?([0-9]*);(?s:\\s*?)*url(?s:\\s*?)=(?s:[\\s']*?)([^'\"]+)\"?(?s:[\\s']*?)/?>(?!\\s*</noscript>)",
				"<noscript><meta http-equiv=\"refresh\" content=\"0; URL=/badbrowser.php\"></noscript>", "Noscript");
find("(?<!<noscript>\\s{0,4})<meta http-equiv=\"?refresh\"? content=\"?([0-9]*);(?s:\\s*?)*url(?s:\\s*?)=(?s:[\\s']*?)([^'\"]+)\"?(?s:[\\s']*?)/?>(?!\\s*</noscript>)",
				"<div><meta http-equiv=\"refresh\" content=\"0; URL=/badbrowser.php\"></div>", "Noscript");

	}

	@Test
	public void test() {

		String regex = "John (?!Smith)[A-Z]\\w+";
		Pattern pattern = Pattern.compile(regex);

		String candidate = "I think that John Smith ";
		candidate += "is a fictional character. His real name ";
		candidate += "might be John Jackson, John Westling, ";
		candidate += "or John Holmes for all we know.";

		Matcher matcher = pattern.matcher(candidate);

		String tmp = null;

		while (matcher.find()) {
			tmp = matcher.group();
			System.out.println("MATCH:" + tmp);
		}
	}

    @Test
    public void testNDFA() {
        find("regex|regex not", "regex not", "text directre or regexp directed");
    }

	@Test
	public void testQuantifier() {
		// <p>hi<b>Bob</b></p>
		find("<.*>", "<p>hi<b>Bob</b></p>", "default (greedy)");

		// null
		find("<.*+>", "<p>hi<b>Bob</b></p>", "posessive");

		// <p>
		find("<.*?>", "<p>hi<b>Bob</b></p>", "reluctant (lazy)");
	}

	private static String find(String pattern, String where, String msg) {
		Pattern pattern1 = Pattern.compile(pattern, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern1.matcher(where);
		String result = "";

		while (matcher.find()) {
			result += matcher.group() + " ~ ";
		}

		System.out.printf("%-25s : %s\n", msg, result);
		return result;
	}
	private static String findNamed(String pattern, String where, String groupName) {
		Pattern pattern1 = Pattern.compile(pattern, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern1.matcher(where);
		String result = "";

		while (matcher.find()) {
			String group = matcher.group(groupName);
			if (group != null) {
				result += group + " ~ ";
			}
		}

		System.out.printf("%-10d : %s\n", cnt.incrementAndGet(), result);
		return result;
	}


}
