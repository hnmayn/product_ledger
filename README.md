# product_ledger
商品情報(商品名/値段/サイズ/素材/説明/在庫数/活動状況)を管理します。<br>
ログイン認証、登録/更新/削除などの基本的な操作が含まれています。

## Description
ログイン後のTOPページは在庫数10点未満の商品をピックアップし、発注が必要な商品を表示しています。<br>
検索画面では、チェックボックスで廃盤を含むか否かを選択し、検索を行うことができます。<br>

## Demo
[![Image from Gyazo](https://i.gyazo.com/e03af93821b0b1b93743c3c758a1c9c8.gif)](https://gyazo.com/309bf71e357947d0fba4de8d9670b657)

## Dependency
- Eclipse Java EE 2020-12
- Taglibs Standerd Impl 1.2.5
- Jackson Dataind 2.13.1
- MySQL 8.0.31
- Hibernate 5.4.28
- MySQL JDBC Driver 8.0.23
- Lombok 1.18.16

## Usage
1. 以下のURLにアクセスし、ログイン画面が表示されます。<br>
``http://localhost:8080/product_ledger/?action=Auth&command=showLogin``
2. ユーザー名、パスワードを入力し、ログインします。
(employee number:1, password:1)

## Authors
Ayano.Honma

## Reference
[レイアウト-1](https://saruwakakun.com/html-css/reference/buttons#section1)<br>
[レイアウト-2](https://jajaaan.co.jp/css/css-headline/)<br>
