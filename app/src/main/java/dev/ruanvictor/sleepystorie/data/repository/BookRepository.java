package dev.ruanvictor.sleepystorie.data.repository;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.ruanvictor.sleepystorie.R;
import dev.ruanvictor.sleepystorie.data.model.Book;

public class BookRepository {
    List<Book> books = Arrays.asList(
            new Book(1, "O Pequeno Príncipe", "Antoine de Saint-Exupéry", "Nesta clássica história que marcou gerações de leitores em todo o mundo, um piloto cai com seu avião no deserto do Saara e encontra um pequeno príncipe, que o leva a uma jornada filosófica e poética através de planetas que encerram a solidão humana", R.drawable.pequeno_principe),
            new Book(2, "Amiga ursa: Uma história triste, mas com final feliz", "Rita Lee", "Em seu novo livro, Rita conta a história da ursa Rowena, que se chamava Marsha e veio parar no Brasil vítima de tráfico de animais. Durante anos, ela foi maltratada em circos e zoológicos até ser resgatada e ir para o Rancho dos Gnomos, onde vive seu tão aguardado final feliz. Pensando que as crianças podem mudar o planeta, Rita é a vovó Ritinha, umas das personagens da obra e grande protetora dos animais. De maneira leve e divertida, ela aborda temas como geografia, biologia, a importância da preservação do meio ambiente – e, é claro, o respeito aos animais!", R.drawable.amiga_ursa),
            new Book(3, "365 Histórias Para Dormir - Vol.1", "Disney Pixar", "Encante-se com 365 histórias para ler durante todo o ano! De janeiro a dezembro, mergulhe todas as noites no maravilhoso universo Disney, alce voo com seus heróis favoritos e aventure-se em terras repletas de magia e sonhos com este livro encantadorq ue BRILHA NO ESCURO!", R.drawable.as_365_historias),
            new Book(4, "O Coelho Caco", "Editora Ciranda Cultural", "Conheça a história do coelho Caco e encante-se com este livro ideal para pequeninos.", R.drawable.coelho_caco),
            new Book(5, "O Desenho Infantil", "Nancy Rabello", "A autora faz um estudo minucioso dos desenhos, do desenvolvimento do grafismo infantil, das suas etapas, das cores e seus simbolismos e da localização do desenho no papel, assim como as fases são estudadas por autores como Piaget, Vygotsky, Lowenfeld, Derdyk, entre outros, e faz este estudo baseada na Arteterapia.", R.drawable.desenho_infantil),
            new Book(6, "A linda garota de Angola", "Ana Gizélia Vieira", "Ágata é uma menina de Angola que vem viver no Brasil, ela vai contar pra você um pouco do seu país e sua cultura e de como ela chegou aqui no Brasil, venha conhecer e se encantar com a linda menina de Angola.", R.drawable.linda_garota),
            new Book(7, "Menina bonita do laço de fita", "Ana Maria Machado", "Uma linda menina negra desperta a admiração de um coelho branco, que deseja ter uma filha tão pretinha quanto ela. Cada vez que ele lhe pergunta qual o segredo de sua cor, ela inventa uma história. O coelho segue todos os “conselhos” da menina, mas continua branco.", R.drawable.menina_bonita),
            new Book(8, "Princesa Arabela, mimada que só ela!", "Mylo Freeman", "A pequena Arabela está acostumada a conseguir tudo o que quer. E tem muito mais do que precisa. Pobres dos pais, o rei e a rainha, que precisam conseguir um elefante de presente para a filha! Só que é o próprio “bichinho” de estimação que acaba ensinando a Arabela uma importante lição: ela não é o único ser do mundo cheio de vontades.", R.drawable.princesa_arabela),
            new Book(9, "Sulwe", "Lupita Nyong'o", "SULWE TEM A PELE DA COR DA MEIA-NOITE.Ela é mais escura que todos de sua família. Ela é mais escura que todos de sua escola.A Sulwe só queria ser bonita e cheia de luz como sua mãe e sua irmã. Quando ela menos esperava, uma jornada mágica no céu da noite abriu seus olhos e fez com que tudo mudasse.", R.drawable.sulwe),
            new Book(10, "Poemas Boborildos", "Eva Furnari", "Os Boborildos são bichos dramáticos e um pouco bobos também. Além disso, eles têm o péssimo hábito de se meter em encrencas. Neste livro, apresentamos alguns dos problemas desses curiosos animais, e o leitor, se quiser, poderá fazer algumas continhas para resolvê-los.", R.drawable.poema_boborildos),
            new Book(11, "Amoras", "Emicida", "Em seu primeiro livro infantil, Emicida conta uma história cheia de simplicidade e poesia, que mostra a importância de nos reconhecermos nos pequenos detalhes do mundo. Na música “Amoras”, Emicida canta: “Que a doçura das frutinhas sabor acalanto/ Fez a criança sozinha alcançar a conclusão/ Papai que bom, porque eu sou pretinha também”. E é a partir desse rap que um dos artistas brasileiros mais influentes da atualidade cria seu primeiro livro infantil e mostra, através de seu texto e das ilustrações de Aldo Fabrini, a importância de nos reconhecermos no mundo e nos orgulharmos de quem somos — desde criança e para sempre.", R.drawable.amoras_capa),
            new Book(12, "O Menino Maluquinho", "Ziraldo Alves Pinto", "Um menininho traquinas, diziam. Tinha macaquinhos no sótão, deitava e rolava, fazendo confusão. Um anjinho, um saci? Alegria da casa, liderava a garotada. Namorador, fazia versinhos, compunha canções, inventava brincadeiras. Era sabido, um amigão. “Menino Maluquinho”, diziam sorrindo as pessoas. Não era, não! Só mais tarde descobriram que tinha sido um garotinho muito amado e, por isso mesmo, muito feliz.", R.drawable.menino_maluquinho)
            );

    public List<Book> getAllBooks() {
        return this.books;
    }

    public Book getBookById(int id){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return books.parallelStream().filter(book -> book.getId() == id).findFirst().get();
        } else {
            for (Book book : books) {
                if(book.getId() == id) {
                    return book;
                }
            }
        }
        return null;
    }
}
