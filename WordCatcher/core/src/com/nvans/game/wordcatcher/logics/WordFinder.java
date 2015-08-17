package com.nvans.game.wordcatcher.logics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import java.io.*;

/**
 * Класс-синглтон.
 *
 * Заполняет HashMap<String, Integer> словами из файла
 * Ключ - это слово (оно не будет повторяться, даже если в файле повторы есть)
 * Значение - это количество букв в слове
 *
 * Created by nvans on 26.11.2014.
 */
public class WordFinder {
    //Экземпляр класса
    private static WordFinder instance;

    //Список слов и длина каждого из них
    private HashMap<String, Integer> words;

    //Список слов без длины, формируется из HashMap
    //чтобы избежать повторений
    private ArrayList<String> wordsList;

    //путь к файлу со словами
    private String fName;

    private Random random;


    /**
     * Конструктор.
     * Здесь инициализируем наш HashMap
     */
    private WordFinder() {
        words = new HashMap<String, Integer>();
    }

    /**
     * Метод для заполнения HashMap словами из файла.
     * Вычитываем слова из файла и помещаем в HashMap
     */
    private void fillWordsMap() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fName));
            String tmpWord = "";
            while (reader.ready()) {
                tmpWord = reader.readLine();
                words.put(tmpWord, tmpWord.length());
            }
            reader.close();

            wordsList = new ArrayList<String>(words.keySet());

            for (String words : wordsList) {
                System.out.println(words);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Геттер экземпляра синглтона с ленивой инициализацией.
     * Этот метод - единственный способ создать
     * экземпляр класса.
     */
    public static WordFinder getInstance() {
        if (instance == null) {
            instance = new WordFinder();
        }
        return instance;
    }

    /**
     * Сеттер пути к файлу со словами.
     */

    public void setFileName(String fName) {
        this.fName = fName;
        fillWordsMap();
    }


    /**
     * Метод возвращает массив слов
     */
    @Deprecated
    public String[] getWords(int length) {
        ArrayList<String> list = new ArrayList<String>();

        for (Map.Entry<String, Integer> pair : words.entrySet()) {

            if (pair.getValue().equals(length)){
                list.add(pair.getKey());
            }
        }
        list.trimToSize();

        String[] strings = new String[list.size()];

        for (int i = 0; i < list.size() ; i++) {
            strings[i] = list.get(i);
        }
        list.clear();

        return strings;
    }

    /**
     * Этот метод проверяет содержит ли наш список слов искомое слово
     */
    public boolean isContain(String word) {

        for (Map.Entry<String, Integer> pair : words.entrySet()) {
            if (pair.getKey().equals(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }


    /**
     * Этот метод возвращает случайное слово из диапазона wordList.size()
     *
     */
    public String getRandomWord() {
        if (random == null) {
            random = new Random();
            System.out.println("create new random object");
        }

        return wordsList.get(random.nextInt(wordsList.size() - 1));
    }

    /**
     * Этот метод возвращает случайное слово из диапазона wordList.size()
     * и учитывает длину слова
     *
     */
    public String getRandomWord(int wordLength) {
        if (!words.containsValue(wordLength))
            return "ERROR! List doesn't contains any word with length " + wordLength;

        if (random == null) {
            random = new Random();
            System.out.println("create new random object");
        }

        String word;

        while (true) {
            word = getRandomWord();
            if (word.length() != wordLength) {
                continue;
            } else
                return word;
        }
    }

}
