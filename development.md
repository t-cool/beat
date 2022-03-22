# beat

## プロジェクトの概要

* アーキテクチャ
[シングルページアプリケーション(SPA)](https://en.wikipedia.org/wiki/Single-page_application)

* 言語
  - フロントエンドは [ClojureScript](https://clojurescript.org/) に ([re-frame](https://github.com/day8/re-frame)) を加えたものです。

* 依存関係
  - UIフレームワーク : 
    - [re-frame](https://github.com/day8/re-frame)
    - [Reagent](https://github.com/reagent-project/reagent) ->  [React](https://github.com/facebook/react)

* ビルドツール
  - [shadow-cljs](https://github.com/thheller/shadow-cljs)

* 開発ツール
  - [CLJS DevTools](https://github.com/binaryage/cljs-devtools)
  - [CIDER](https://github.com/clojure-emacs/cider)

## ディレクトリ構造

* [`/`](./) :  プロジェクト設定ファイル

* [`dev/`](dev/): [dev](#running-the-app) プロファイルでのみコンパイルされたソースファイルです。
  - [`user.cljs`](dev/cljs/user.cljs): [ClojureScript REPL](#connecting-to-the-browser-repl-from-a-terminal)での開発中に使用するシンボルです。

* [`resources/public/`](resources/public/): SPA のルートディレクトリです。
[dev](#running-the-app) / [prod](#production) プロファイルは直近のビルドに依存します。

  - [`index.html`](resources/public/index.html): SPAのトップページです。
    - 以下の `div` でレンダリングされる動的な SPA コンテンツです。
        ``html
        <div id="app"></div>.
        ```
    - ヘッダー、フッター、他のスクリプトやスタイルへのリンクの追加など、カスタマイズが可能です。
  - 生成されるディレクトリとファイル
    - [dev](#running-the-app) または [prod](#production) プロファイルでビルドしたときに作成されます。
    - `js/compiled/`: コンパイルされたCLJS (`shadow-cljs`)です。ソース管理では追跡されません。[`.gitignore`](.gitignore)を参照してください。

* [`src/beat/`](src/beat/): SPA のソースファイルです。以下のファイルを含みます。

  - [`core.cljs`](src/beat/core.cljs): SPAのエントリーポイントである `init` が含まれています。

* [`.github/workflows/`](.github/workflows/) : 以下のものが含まれています。
  - [github actions](https://github.com/features/actions) : GitHub Actions のパイプラインです。
  - [`test.yaml`](.github/workflows/test.yaml) : テスト用のパイプラインです。

## エディター / IDE

Clojure/ClojureScript 開発をサポートするお好みのエディタまたは IDE を使用してください。

[Clojure tools](https://clojure.org/community/resources#_clojure_tools) には、いくつかの一般的なオプションがあります。

## 環境設定
1. [バージョン 8 以降の JDK](https://openjdk.java.net/install/) をインストールしてください。

2. [Node.js](https://nodejs.org/) をインストールしてください。

3. このリポジトリをクローンし、`beat`プロジェクトのルートディレクトリでターミナルを開きます。

## ブラウザの設定

[`shadow-cljs`](https://github.com/thheller/shadow-cljs) のホットリローディングとの干渉を防ぐため、開発者ツールを開いているときは、ブラウザのキャッシュを無効にする必要があります。

## Chrome

1. [DevTools](https://developers.google.com/web/tools/chrome-devtools/)を開く。

2. DevTools の設定を開く

3. 左側のナビゲーションメニューで、`Preferences`を選択します。

4. ネットワーク」の見出しの下にある「キャッシュを無効にする（DevToolsが開いている間）」オプションを有効にします。

5. 「コンソール」の見出しの下にある「カスタムフォーマッタを有効にする」オプションを有効にします。

## 開発

### アプリの実行

一時的にローカルの Web サーバを起動し、`dev` プロファイルでアプリをビルドして、アプリを提供します。ブラウザテストランナー、カルマテストランナーをホットリロードで実行します。

```sh
$ npm install
$ npx shadow-cljs wach app
```

出力が出るまでに 20 秒以上、完了までに 40 秒以上かかることがあります。

出力に `[:app] Build completed` と表示されたら、次の場所をブラウザで開いてください。[http://localhost:8280/](http://localhost:8280/)。

[`shadow-cljs`](https://github.com/thheller/shadow-cljs)は、ブラウザの保存時に自動的に ClojureScript のコードをプッシュします。

いくつかのよくある問題を防ぐには、以下を参照してください。
[ClojureScriptにおけるホットリロード：避けるべきこと](https://code.thheller.com/blog/shadow-cljs/2019/08/25/hot-reload-in-clojurescript.html#things-to-avoid)。

ブラウザでアプリを開くと、[ClojureScript のブラウザ用 REPL](https://clojurescript.org/reference/repl#using-the-browser-as-an-evaluation-environment) に接続することができます。

## VS CodeからCalvaでブラウザREPLに接続する

[Calva](https://github.com/BetterThanTomorrow/calva) の説明は [re-frame-template README](https://github.com/day8/re-frame-template) を参照してください。Calva のドキュメントについては、https://calva.io も参照してください。

## `shadow-cljs` のアクションを実行する

[shadow-cljs CLI](https://shadow-cljs.github.io/docs/UsersGuide.html#_command_line) の一覧をご覧ください。
アクションを実行します。

```sh
npx shadow-cljs --help
```

出力されるまでに 10 秒以上かかることがありますが、ご了承ください。また、表示されているアクションの中にはサポートされない場合があります。

このプロジェクトのビルド ID で shadow-cljs アクションを実行します（コロンなしで、`app` だけです）。

```sh
npx shadow-cljs <アクション> app
```
### デバッグのログ

[`config.cljs`](src/cljs/beat/config.cljs) の `debug?` 変数は、デフォルトで `true` に設定されています。

[`dev`](#running-the-app) ビルドでは `dev`、 [`prod`](#production) ビルドでは `false` となります。

`dev` ビルドでのみ実行されるログやその他のタスクには `debug?` を使用します。

```clj
(ns beat.example
  (:require [beat.config :as config])

(when config/debug?
  (println "This message will appear in the browser console only on dev builds."))
```

## プロダクション

`prod` プロファイルでアプリをビルドします。

```sh
$ npm install
$ npm run release
```

出力が表示されるまで 15 秒以上、完了するまで 30 秒以上かかる場合があります。

`resources/public/js/compiled` ディレクトリが作成され、コンパイルされた `app.js` と `manifest.edn` ファイルを含みます。


## さいごに
意味順を日本中に届けるために、