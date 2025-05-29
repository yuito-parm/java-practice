# 🐾 Animal Inventory Manager

Java × GUI × JSON で作った「生き物管理アプリ」！

## 📆 アプリ概要

このアプリは、生き物を在庫として管理できるGUIアプリです。
個体の名前、価格、在庫数、現在の状態、過去の生育年表（時刻付き）を記録・表示・検索できます。

## ✨ 主な機能

* 商品（動物）の登録・編集・削除
* 在庫情報を一覧表示
* 名前で検索
* 状態の年表を記録＆表示（時刻付き）
* JSONファイルによるデータ保存
* Java SwingによるGUI実装

## 📷 スクリーンショット

※ここに画像を貼る（あとで指示するね！）

## 🚀 使い方

### 1. 必要なライブラリを用意

[Gson（JSON処理用）](https://github.com/google/gson)を使っています。

```bash
javac -cp gson-2.8.6.jar *.java
java -cp .:gson-2.8.6.jar Step50_AnimalAppFinal
```

※Macの場合は `:`、Windowsは `;`に変更してください。

### 2. アプリを使う

アプリ起動後、GUI上で操作してください。

## 💠 技術スタック

* Java 17
* Java Swing（GUI）
* Gson（JSON処理）
* オブジェクト指向プログラミング
* ファイル操作 / 入出力処理

## 📁 ディレクトリ構成

```
Java-practice/
├─ Animal.java
├─ Status.java
├─ Step50_AnimalAppFinal.java
├─ animals.json
└─ ...（他の補助ファイル）
```

## 📌 補說

* JSONファイルはアプリ起動・終了時に自動保存されます。
* データが存在しない場合は新規作成されます。

## 👨‍💻 製作背景・目的

「ポートフォリオ用にGUI付きアプリを作りたい！」という目標で、Javaの基本〜ファイル操作〜JSON保存〜GUI表示まで、すべて一貫して学習＆実装しました。

## 🗂 ライセンス

MIT License
