package com.studiobt.descobrindomundoscomferreiragular;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ObrasActivity extends AppCompatActivity {

    ListView listView;
    ManagerObras managerObras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obras);

        listView = (ListView) findViewById(R.id.list_obras);

        addObras();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, managerObras.getNames());

        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onResume() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                goRead(i);
            }
        });

        super.onResume();
    }

    public void goRead(int id){
        Intent intent = new Intent(this, ReadActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    public void addObras(){
        managerObras = new ManagerObras();
        managerObras.removeAll();

        ArrayList<Question> questions;

        questions = new ArrayList<>();
        questions.add(new Question("O nome do pai de Ferreira Gullar era Newton Ferreira?", true));
        questions.add(new Question("Ferreira Gullar é pseudônimo de José Ribamar Ferreira?", true));
        questions.add(new Question("Ferreira Gullar foi postulante da Academia Brasileira de Letras?", true));
        questions.add(new Question("Segundo o texto, Ferreira Gullar afirma que todo mundo no Maranhão é Ribamar?", true));
        questions.add(new Question("Ferreira Gullar nasceu em São Paulo?", false));
        questions.add(new Question("Segundo o texto, Ferreira Gullar na verdade não exisitiu?", false));
        questions.add(new Question("Ferreira Gullar foi fundador do Romantismo?", false));
        questions.add(new Question("Ferreira Gullar nasceu em 2000?", false));

        managerObras.addObra("Quem foi Ferreira Gullar?", "Ferreira Gullar, pseudônimo de José Ribamar Ferreira (São Luís, 10 de setembro de 1930 – Rio de Janeiro, 4 de dezembro de 2016), foi um escritor, poeta, crítico de arte, biógrafo, tradutor, memorialista e ensaísta brasileiro e um dos fundadores do neoconcretismo. Foi o postulante da cadeira 37 da Academia Brasileira de Letras, na vaga deixada por Ivan Junqueira, da qual tomou posse em 5 de dezembro de 2014. \n Ferreira Gullar nasceu em São Luís, em 10 de setembro de 1930, com o nome de José Ribamar Ferreira. É um dos onze filhos do casal Newton Ferreira e Alzira Ribeiro Goulart.\n" +
                "Sobre o pseudônimo, o poeta declarou o seguinte: \"Gullar é um dos sobrenomes de minha mãe, o nome dela é Alzira Ribeiro Goulart, e Ferreira é o sobrenome da família, eu então me chamo José Ribamar Ferreira; mas como todo mundo no Maranhão é Ribamar, eu decidi mudar meu nome e fiz isso, usei o Ferreira que é do meu pai e o Gullar que é de minha mãe, só que eu mudei a grafia porque o Gullar de minha mãe é o Goulart francês; é um nome inventado, como a vida é inventada eu inventei o meu nome\".\n" +
                "Segundo Mauricio Vaitsman, ao lado de Bandeira Tribuzi, Luci Teixeira, Lago Burnet, José Bento, José Sarney e outros escritores, fez parte de um movimento literário difundido através da revista que lançou o pós-modernismo no Maranhão, A Ilha, da qual foi um dos fundadores. Até sua morte, muitos o consideravam o maior poeta vivo do Brasil e não seria exagero dizer que, durante suas seis décadas de produção artística, Ferreira Gullar passou por todos os acontecimentos mais importantes da poesia brasileira e participou deles.\n" +
                "Morando no Rio de Janeiro, participou do movimento da poesia concreta, sendo então um poeta extremamente inovador, escrevendo seus poemas, por exemplo, em placas de madeira, gravando-os.\n" +
                "Em 1956 participou da exposição concretista que é considerada o marco oficial do início da poesia concreta, tendo se afastado desta em 1959, criando, junto com Lígia Clark e Hélio Oiticica, o neoconcretismo, que valoriza a expressão e a subjetividade em oposição ao concretismo ortodoxo. Posteriormente, ainda no início dos anos de 1960, se afastará deste grupo também, por concluir que o movimento levaria ao abandono do vínculo entre a palavra e a poesia, passando a produzir uma poesia engajada e envolvendo-se com os Centros Populares de Cultura (CPCs).\n" +
                "Em 2014, ele foi considerado um imortal na Academia Brasileira de Letras.\n" +
                "Ferreira Gullar morreu em 4 de dezembro de 2016, na cidade do Rio de Janeiro em decorrência de vários problemas respiratórios que culminaram em uma pneumonia. O velório do escritor foi realizado inicialmente na Biblioteca Nacional, pois esse era um desejo de Gullar. Dali, o corpo foi levado em um cortejo fúnebre até a Academia Brasileira de Letras no Rio de Janeiro. Uma semana antes de morrer, Ferreira Gullar pediu à filha Luciana para que o levasse até a Praia de Ipanema. O enterro foi no Cemitério de São João Batista em Botafogo no Rio. Gullar ocupava a trígésima sétima cadeira da ABL.\n", questions);

        questions = new ArrayList<>();
        questions.add(new Question("Segundo o poema, uma parte de mim é todo mundo e outra parte é ninguém?", true));
        questions.add(new Question("Segundo o poema, uma parte de mim é celular e outra parte é notebook?", false));
        questions.add(new Question("Segundo o poema, uma parte de mim é coisa e outra parte é louza?", false));
        questions.add(new Question("Segundo o poema, uma parte de mim, é fundo sem fundo?", true));
        questions.add(new Question("Segundo o poema, uma parte de mim, é solidão?", true));
        questions.add(new Question("Segundo o poema, uma parte de mim, é todo mundo?", true));
        questions.add(new Question("Segundo o poema, uma parte de mim, é almoça e janta?", true));
        questions.add(new Question("Segundo o poema, uma parte de mim, é preguiçosa?", false));
        questions.add(new Question("Segundo o poema, uma parte de mim, é gentil?", false));
        questions.add(new Question("Segundo o poema, uma parte de mim, é tempo?", false));
        questions.add(new Question("Segundo o poema, uma parte de mim, é vento?", false));

        managerObras.addObra("Poema: Traduzir-se – Ferreira Gullar", "Traduzir-se\n" +
                "\n" +
                "Uma parte de mim\n" +
                "é todo mundo:\n" +
                "outra parte é ninguém:\n" +
                "fundo sem fundo.\n" +
                "Uma parte de mim\n" +
                "é multidão:\n" +
                "outra parte estranheza\n" +
                "e solidão.\n" +
                "Uma parte de mim\n" +
                "pesa, pondera:\n" +
                "outra parte\n" +
                "delira.\n" +
                "Uma parte de mim\n" +
                "almoça e janta:\n" +
                "outra parte\n" +
                "se espanta.\n" +
                "Uma parte de mim\n" +
                "é permanente:\n" +
                "outra parte\n" +
                "se sabe de repente.\n" +
                "Uma parte de mim\n" +
                "é só vertigem:\n" +
                "outra parte,\n" +
                "linguagem.\n" +
                "Traduzir-se uma parte\n" +
                "na outra parte\n" +
                "– que é uma questão\n" +
                "de vida ou morte –\n" +
                "será arte?\n", questions);

        questions = new ArrayList<>();
        questions.add(new Question("Segundo poema, o verão levou algo entre nuvens e risos?", true));
        questions.add(new Question("O poema fala de algum incendio na cama?", true));
        questions.add(new Question("O poema fala de algum apelo da noite?", true));
        questions.add(new Question("Segundo o poema, a poesia é o presente?", true));

        questions.add(new Question("Segundo poema, vale a pena reconstruir com palavras o que verão levou?", false));
        questions.add(new Question("Segundo o poema, o apelo é da tarde?", false));
        questions.add(new Question("O poema fala o nome de alguma pessoa?", false));
        questions.add(new Question("O autor do poema é Ferreira Gullar?", true));

        managerObras.addObra("Poema: No corpo – Ferreira Gullar", "No corpo\n" +
                "\n" +
                "De que vale tentar reconstruir com palavras\n" +
                "O que o verão levou\n" +
                "Entre nuvens e risos\n" +
                "Junto com o jornal velho pelos ares\n" +
                "O sonho na boca, o incêndio na cama,\n" +
                "o apelo da noite\n" +
                "Agora são apenas esta\n" +
                "contração (este clarão)\n" +
                "do maxilar dentro do rosto.\n" +
                "A poesia é o presente.\n", questions);

        questions = new ArrayList<>();
        questions.add(new Question("No poema existe um mar azul?", true));
        questions.add(new Question("No poema existe um marco azul?", true));
        questions.add(new Question("No poema existe um arco azul?", true));
        questions.add(new Question("No poema existe um ar azul?", true));

        questions.add(new Question("No poema existe um beijo azul?", false));
        questions.add(new Question("No poema existe um homem azul?", false));
        questions.add(new Question("No poema existe um mar verde?", false));
        questions.add(new Question("No poema existe um planeta amarelo?", false));

        managerObras.addObra("Poema: Poemas Neoconcretos I – Ferreira Gullar", "Poemas Neoconcretos I\n" +
                "\n" +
                "mar azul\n" +
                "\n" +
                "mar azul marco azul\n" +
                "\n" +
                "mar azul marco azul barco azul\n" +
                "\n" +
                "mar azul marco azul barco azul arco azul\n" +
                "\n" +
                "mar azul marco azul barco azul arco azul ar azul\n", questions);

        questions = new ArrayList<>();
        questions.add(new Question("Segundo o poema, o sofrimento é fruto da alegria?", true));
        questions.add(new Question("Segundo o poema, o sofrimento é o averso ardente da alegria?", true));
        questions.add(new Question("Segundo o poema, a vida só consome o que a alimenta?", true));
        questions.add(new Question("O poema afirma que da alegria foste ao fundo?", true));

        questions.add(new Question("Segundo o poema, a alegria gera alegria?", false));
        questions.add(new Question("O poema, afirma que a tristeza é fruto de rancor?", false));
        questions.add(new Question("O poema mostra um personagem que não era feliz?", false));
        questions.add(new Question("Segundo o poema, as pessoas precisam ter raiva?", false));

        managerObras.addObra("Poema: Aprendizado – Ferreira Gullar", "Aprendizado\n" +
                "\n" +
                "Do mesmo modo que te abriste à alegria\n" +
                "abre-te agora ao sofrimento\n" +
                "que é fruto dela\n" +
                "e seu avesso ardente.\n" +
                "\n" +
                "Do mesmo modo\n" +
                "que da alegria foste\n" +
                "ao fundo\n" +
                "e te perdeste nela\n" +
                "e te achaste\n" +
                "nessa perda\n" +
                "deixa que a dor se exerça agora\n" +
                "sem mentiras\n" +
                "nem desculpas\n" +
                "e em tua carne vaporize\n" +
                "toda ilusão\n" +
                "\n" +
                "que a vida só consome\n" +
                "o que a alimenta.\n", questions);

        questions = new ArrayList<>();
        questions.add(new Question("O poema afirma que aqui me tenho sem mim?", true));
        questions.add(new Question("O poema afirma que não me conheço?", true));
        questions.add(new Question("Segundo o poema, sou apenas um bixo?", true));
        questions.add(new Question("Segundo o poema, sou transparente?", true));

        questions.add(new Question("O poema afirma que tudo lembro?", false));
        questions.add(new Question("O poema afirma que não sou transparente?", false));
        questions.add(new Question("Segundo o poema, eu sei de tudo?", false));
        questions.add(new Question("Segundo o poema sou amarelo?", false));

        managerObras.addObra("Poema: Um Instante – Ferreira Gullar", "Um instante \n" +
                "\n" +
                "Aqui me tenho\n" +
                "Como não me conheço\n" +
                "nem me quis\n" +
                "sem começo\n" +
                "nem fim\n" +
                "aqui me tenho\n" +
                "sem mim\n" +
                "nada lembro\n" +
                "nem sei\n" +
                "à luz presente\n" +
                "sou apenas um bicho\n" +
                "transparente. \n" +
                "Um instante, \n", questions);

        questions = new ArrayList<>();
        questions.add(new Question("Segundo o poema, a poesia não respeita mãe nem pai?", true));
        questions.add(new Question("Segundo poema, a poesia relincha?", true));
        questions.add(new Question("O poema afirma que a poesia embala no colo os que tem sede de felicidade?", true));
        questions.add(new Question("O poema afirma que a poesia beija nos olhos os que ganham mal?", true));

        questions.add(new Question("Segundo o poema, a poesia é má?", false));
        questions.add(new Question("Segundo o poema, a poesia é cruel?", false));
        questions.add(new Question("O poema afirma que a poesia é mentira?", false));
        questions.add(new Question("O poema nega que a poesia quando chega não respeita nada?", false));

        managerObras.addObra("Poema: Subversiva – Ferreira Gullar", "Subversiva\n" +
                "\n" +
                "A poesia\n" +
                "Quando chega\n" +
                "Não respeita nada.\n" +
                "Nem pai nem mãe.\n" +
                "Quando ela chega\n" +
                "De qualquer de seus abismos\n" +
                "Desconhece o Estado e a Sociedade Civil\n" +
                "Infringe o Código de Águas\n" +
                "Relincha\n" +
                "Como puta\n" +
                "Nova\n" +
                "Em frente ao Palácio da Alvorada.\n" +
                "E só depois\n" +
                "Reconsidera: beija\n" +
                "Nos olhos os que ganham mal\n" +
                "Embala no colo\n" +
                "Os que têm sede de felicidade\n" +
                "E de justiça.\n" +
                "E promete incendiar o país.\n", questions);

        questions = new ArrayList<>();
        questions.add(new Question("O poema afirma que os mertos vêem o mundo pelos olhos dos vivos?", true));
        questions.add(new Question("O poema afirm que eventualmente os mortos ouvem com nossos ouvidos?", true));
        questions.add(new Question("O poema afirma que os mortos eventualmente ouvem ventanias?", true));
        questions.add(new Question("O poema afirma que os mortos são ausentes de corpo e alma?", true));

        questions.add(new Question("O poema afirma que os mortos sabem cantar?", false));
        questions.add(new Question("O poema afirma que os mortos eventualmente choram?", false));
        questions.add(new Question("O poema afirma que os vivos são mortos?", false));
        questions.add(new Question("O poema afirma que os vivos são tristes?", false));

        managerObras.addObra("Poema: Os mortos – Ferreira Gullar", "Os mortos\n" +
                "\n" +
                "os mortos vêem o mundo\n" +
                "pelos olhos dos vivos\n" +
                "\n" +
                "eventualmente ouvem,\n" +
                "com nossos ouvidos,\n" +
                "certas sinfonias\n" +
                "algum bater de portas,\n" +
                "ventanias\n" +
                "\n" +
                "Ausentes\n" +
                "de corpo e alma\n" +
                "misturam o seu ao nosso riso\n" +
                "se de fato\n" +
                "quando vivos\n" +
                "acharam a mesma graça.\n", questions);

        questions = new ArrayList<>();
        questions.add(new Question("O narrador pede que quando a moça branca for embora o leve?", true));
        questions.add(new Question("O narrador pede que a moça o leve no coração?", true));
        questions.add(new Question("O narrador pede que a moça o leve no esquecimento?", true));
        questions.add(new Question("O narrador pede que a moça o carregue pela mão?", true));

        questions.add(new Question("O narrador pede que a moça o deixe só?", false));
        questions.add(new Question("O narrador pede que a moça o liberte?", false));
        questions.add(new Question("O narrador pede que a moça lhe ache?", false));
        questions.add(new Question("A moça nega algo ao narrador?", false));

        managerObras.addObra("Poema: Cantiga para não morrer – Ferreira Gullar", "Cantiga para não morrer\n" +
                "\n" +
                "Quando você for se embora,\n" +
                "moça branca como a neve,\n" +
                "me leve.\n" +
                "\n" +
                "Se acaso você não possa\n" +
                "me carregar pela mão,\n" +
                "menina branca de neve,\n" +
                "me leve no coração.\n" +
                "\n" +
                "Se no coração não possa\n" +
                "por acaso me levar,\n" +
                "moça de sonho e de neve,\n" +
                "me leve no seu lembrar.\n" +
                "\n" +
                "E se aí também não possa\n" +
                "por tanta coisa que leve\n" +
                "já viva em seu pensamento,\n" +
                "menina branca de neve,\n" +
                "me leve no esquecimento.\n", questions);

        questions = new ArrayList<>();
        questions.add(new Question("O narrador afirma que a buscou na catástrofe de aurora?", true));
        questions.add(new Question("O narrador afirma que sempre que lhe acerca vai-se embora?", true));
        questions.add(new Question("O narrador afirma que a persegue lúcido e demente?", true));
        questions.add(new Question("O poema afirma que colho a ausência que me queima a mão?", true));

        questions.add(new Question("O narrador afirma que não prometeu possí-la?", false));
        questions.add(new Question("O narrador afirma que a buscou na cozinha?", false));
        questions.add(new Question("O pema afirma que que não a busca?", false));
        questions.add(new Question("O narrador deseja apenas viajar?", false));

        managerObras.addObra("Poema: Prometi-me Possuí-la – Ferreira Gullar", "Prometi-me Possuí-la\n" +
                "\n" +
                "Prometi-me possuí-la muito embora\n" +
                "ela me redimisse ou me cegasse.\n" +
                "Busquei-a na catástrofe da aurora,\n" +
                "e na fonte e no muro onde sua face,\n" +
                "\n" +
                "entre a alucinação e a paz sonora\n" +
                "da água e do musgo, solitária nasce.\n" +
                "Mas sempre que me acerco vai-se embora\n" +
                "como se temesse ou me odiasse.\n" +
                "\n" +
                "Assim persigo-a, lúcido e demente.\n" +
                "Se por detrás da tarde transparente\n" +
                "seus pés vislumbro, logo nos desvãos\n" +
                "\n" +
                "das nuvens fogem, luminosos e ágeis!\n" +
                "Vocabulário e corpo — deuses frágeis —\n" +
                "eu colho a ausência que me queima as mãos.\n", questions);

        questions = new ArrayList<>();
        questions.add(new Question("A primeira estrofe do poema é uma pergunta?", true));
        questions.add(new Question("O poema afirma que estou disperso nas coisas?", true));
        questions.add(new Question("O poema afirma que estou desfeito nas nuvens?", true));
        questions.add(new Question("O narrador afirma que se extraviou no tempo?", true));

        questions.add(new Question("O narrador nega que está disperso nos vivos?", false));
        questions.add(new Question("O narrador afirma que está triste?", false));
        questions.add(new Question("A última estrofe do poema é uma pergunta?", false));
        questions.add(new Question("O poema afirma que deseja ser mais?", false));

        managerObras.addObra("Poema: Extravio – Ferreira Gullar", "Extravio\n" +
                "\n" +
                "Onde começo, onde acabo,\n" +
                "se o que está fora está dentro\n" +
                "como num círculo cuja\n" +
                "periferia é o centro?\n" +
                "\n" +
                "Estou disperso nas coisas,\n" +
                "nas pessoas, nas gavetas:\n" +
                "de repente encontro ali\n" +
                "partes de mim: risos, vértebras.\n" +
                "\n" +
                "Estou desfeito nas nuvens:\n" +
                "vejo do alto a cidade\n" +
                "e em cada esquina um menino,\n" +
                "que sou eu mesmo, a chamar-me.\n" +
                "\n" +
                "Extraviei-me no tempo.\n" +
                "Onde estarão meus pedaços?\n" +
                "Muito se foi com os amigos\n" +
                "que já não ouvem nem falam.\n" +
                "\n" +
                "Estou disperso nos vivos,\n" +
                "em seu corpo, em seu olfato,\n" +
                "onde durmo feito aroma\n" +
                "ou voz que também não fala.\n" +
                "\n" +
                "Ah, ser somente o presente:\n" +
                "esta manhã, esta sala.\n", questions);

        questions = new ArrayList<>();
        questions.add(new Question("O narrador afirma que ouve?", true));
        questions.add(new Question("O narrador afirma que seu corpo é clandestino?", true));
        questions.add(new Question("O poema afirma que meu país é dividido é classes°", true));
        questions.add(new Question("O poema afirma que a noite ocidental está obscenamente acesa?", true));

        questions.add(new Question("O narrador afirma que vê?", false));
        questions.add(new Question("O narrador pede paz?", false));
        questions.add(new Question("O narrador afirma que apenas vê?", false));
        questions.add(new Question("O narrador nega que a noite ocidental está acesa sobre seu país?", false));

        managerObras.addObra("Poema: Madrugada – Ferreira Gullar", "Madrugada\n" +
                "\n" +
                "Do fundo de meu quarto, do fundo\n" +
                "de meu corpo\n" +
                "clandestino\n" +
                "ouço (não vejo) ouço\n" +
                "crescer no osso e no músculo da noite\n" +
                "a noite\n" +
                "\n" +
                "a noite ocidental obscenamente acesa\n" +
                "sobre meu país dividido em classes.\n", questions);

        questions = new ArrayList<>();
        questions.add(new Question("O poema faz referência a um leito de ausência?", true));
        questions.add(new Question("O narrador afirma que em um leito de ausência desperta um longo rio solitário?", true));
        questions.add(new Question("O poema faz alusão a um rio?", true));
        questions.add(new Question("O poema afirma que sobre o leito de sal, sou luz e gesso?", true));

        questions.add(new Question("O narrador afirma que em um leito de presença desperta um longo rio solitário?", false));
        questions.add(new Question("O narrador faz alusão a uma porta?", false));
        questions.add(new Question("O poema afirma que desejo ser um peixe?", false));
        questions.add(new Question("O poema afirma que tudo é nada?", false));

        managerObras.addObra("Poema: Neste Leito de Ausência – Ferreira Gullar", "Neste Leito de Ausência\n" +
                "\n" +
                "Neste leito de ausência em que me esqueço\n" +
                "desperta o longo rio solitário:\n" +
                "se ele cresce de mim, se dele cresço,\n" +
                "mal sabe o coração desnecessário.\n" +
                "\n" +
                "O rio corre e vai sem ter começo\n" +
                "nem foz, e o curso, que é constante, é vário.\n" +
                "Vai nas águas levando, involuntário,\n" +
                "luas onde me acordo e me adormeço.\n" +
                "\n" +
                "Sobre o leito de sal, sou luz e gesso:\n" +
                "duplo espelho — o precário no precário.\n" +
                "Flore um lado de mim? No outro, ao contrário,\n" +
                "de silêncio em silêncio me apodreço.\n" +
                "\n" +
                "Entre o que é rosa e lodo necessário,\n" +
                "passa um rio sem foz e sem começo.\n", questions);

        questions = new ArrayList<>();
        questions.add(new Question("O narrador afirma que seu povo e seu poema crescem juntos?", true));
        questions.add(new Question("O narrador afirma que seu povo no poema vai nascendo?", true));
        questions.add(new Question("O narrador afirma que no povo seu poema está maduro?", true));
        questions.add(new Question("O narrador afirma que devolve ao povo seu poema?", true));

        questions.add(new Question("O narrador afirma que o povo não merece poema?", false));
        questions.add(new Question("O narrador afirma que o povo é como uma planta?", false));
        questions.add(new Question("O narrador afirma que seu povo não tem a nada a ver com poema?", false));
        questions.add(new Question("O narrador nega que devolve ao povo seu poema?", false));

        managerObras.addObra("Poema: Meu povo, meu poema – Ferreira Gullar", "Meu povo, meu poema\n" +
                "\n" +
                "Meu povo e meu poema crescem juntos\n" +
                "como cresce no fruto\n" +
                "a árvore nova\n" +
                "\n" +
                "No povo meu poema vai nascendo\n" +
                "como no canavial\n" +
                "nasce verde o açúcar\n" +
                "\n" +
                "No povo meu poema está maduro\n" +
                "como o sol\n" +
                "na garganta do futuro\n" +
                "\n" +
                "Meu povo em meu poema\n" +
                "se reflete\n" +
                "como a espiga se funde em terra fértil\n" +
                "\n" +
                "Ao povo seu poema aqui devolvo\n" +
                "menos como quem canta\n" +
                "do que planta.\n", questions);

        questions = new ArrayList<>();
        questions.add(new Question("O poeta afirma que seu espaço é o dia de braços abertos?", true));
        questions.add(new Question("O narrador afirma que seu espaço é o dia terrestre?", true));
        questions.add(new Question("O narrador afirma nosso espaço é nossa gente?", true));
        questions.add(new Question("O poeta afirma que o dia gira colado ao planeta?", true));

        questions.add(new Question("O poeta afirma que o espaço é o nada?", false));
        questions.add(new Question("O narrador afirma que nossa gente não importa?", false));
        questions.add(new Question("O narrador pede que esqueçam o espaço?", false));
        questions.add(new Question("O narrador fala sobre a lua?", false));

        managerObras.addObra("Poema: MINHA MEDIDA – Ferreira Gullar", "MINHA MEDIDA\n" +
                "\n" +
                "Meu espaço é o dia\n" +
                "de braços abertos\n" +
                "tocando a fímbria de uma e outra noite\n" +
                "o dia\n" +
                "que gira\n" +
                "colado ao planeta\n" +
                "e que sustenta numa das mãos a aurora\n" +
                "e na outra\n" +
                "um crepúsculo de Buenos Aires\n" +
                "\n" +
                "Meu espaço, cara,\n" +
                "é o dia terrestre\n" +
                "quer o conduzam os pássaros do mar\n" +
                "ou os comboios da Estrada de Ferro Central do Brasil\n" +
                "o dia\n" +
                "medido mais pelo pulso\n" +
                "do que\n" +
                "pelo meu relógio de pulso\n" +
                "\n" +
                "Meu espaço — desmedido —\n" +
                "é o nosso pessoal aí, é nossa\n" +
                "gente,\n" +
                "de braços abertos tocando a fímbria\n" +
                "de uma e outra fome,\n" +
                "o povo, cara,\n" +
                "que numa das mãos sustenta a festa\n" +
                "e na outra\n" +
                "uma bomba de tempo.\n", questions);

        /*
        questions = new ArrayList<>();
        questions.add(new Question("Pergunta", true));
        questions.add(new Question("Pergunta", false));

        managerObras.addObra("Titulo", "Conteudo", questions);
        */
    }
}
