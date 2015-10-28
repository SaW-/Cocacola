# Cocacola
Version 1.5
$ git clone git@github.com:gotgithub/gotgit.git
$ cd gotgit
$ git config user.name "Wang Sheng"
$ git config user.email wangsheng@ossxp.com
$ vi errata.mkd
$ git diff
diff --git a/errata.mkd b/errata.mkd
index b0b68fb..29e40cf 100644
--- a/errata.mkd
+++ b/errata.mkd
@@ -14,5 +14,6 @@
 |     66 | 倒数第11行                | Author（提交者）             |  Author（作者）              | [Github#2](http://github.com/gotgit/gotgit/issues/2)    |
 |    144 | 第1行                     | \`$ **git rev-parse  A^{tree}  A:**  | $ **git rev-parse  A^{tree}  A:**              | [#153](http://redmine.ossxp.com/redmine/issues/153)  |
 |    218 | 第8行                     | 况下，Gits标识出合并冲突，           | 况下，Git标识出合并冲突，                      | [#159](http://redmine.ossxp.com/redmine/issues/159)  |
+|    369 | 第21行                    | 但 `-i` 参数仅当对一个项执行时才有效。 | 但 `-i` 参数仅当对一个项目执行时才有效。     | [Github#3](http://github.com/gotgit/gotgit/issues/3)    |
 |    516 | 倒数第15行                | **oldtag="cat"**             | **oldtag=\`cat\`**           | [#151](http://redmine.ossxp.com/redmine/issues/151)  |
 $ git add -u
$ git commit -m "Fixed #3: should be 项目, not 项."
$ git push
