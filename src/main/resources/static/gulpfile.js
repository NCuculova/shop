var gulp = require('gulp');
var concat = require('gulp-concat');
var tc = require('gulp-angular-templatecache');
var jspretty = require('gulp-js-prettify');

var CSS = [
'bower_components/bootstrap/dist/css/bootstrap.css',
'bower_components/angular-ui-select/dist/select.min.css',
'bower_components/components-font-awesome/css/font-awesome.min.css',
'bower_components/angular-motion/dist/angular-motion.min.css',
'bower_components/angular-loading-bar/build/loading-bar.min.css',
'bower_components/angular-datatables/dist/plugins/bootstrap/datatables.bootstrap.min.css',
'css/main.css'
];

var LIBS = [
'bower_components/jquery/dist/jquery.min.js',
'bower_components/datatables/media/js/jquery.dataTables.min.js',
'bower_components/bootstrap/dist/js/bootstrap.min.js',
'bower_components/angular/angular.min.js',
'bower_components/angular-route/angular-route.min.js',
'bower_components/angular-resource/angular-resource.min.js',
'bower_components/angular-sanitize/angular-sanitize.min.js',
'bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js',
'bower_components/angular-ui-select/dist/select.js',
'bower_components/ng-file-upload/ng-file-upload.js',
'bower_components/angular-strap/dist/angular-strap.min.js',
'bower_components/angular-strap/dist/angular-strap.tpl.min.js',
'bower_components/angular-translate/angular-translate.min.js',
'bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files.min.js',
'bower_components/angular-animate/angular-animate.min.js',
'bower_components/angular-credit-cards/release/angular-credit-cards.js',
'bower_components/angular-loading-bar/build/loading-bar.min.js',
'bower_components/angular-datatables/dist/angular-datatables.min.js',
'bower_components/angular-datatables/dist/plugins/bootstrap/angular-datatables.bootstrap.min.js'
];

var APP = [
  'app/**/**.js'
];

var DEST = 'dist/';

gulp.task('concat-css', function(){
  return gulp.src(CSS)
     .pipe(concat('style.css'))
     .pipe(gulp.dest(DEST));
});

gulp.task('concat-libs', function(){
  return gulp.src(LIBS)
     .pipe(concat('lib.js'))
     .pipe(gulp.dest(DEST));
});

gulp.task('concat-app', function(){
  return gulp.src(APP)
     .pipe(concat('app.js'))
     .pipe(gulp.dest(DEST));
});

gulp.task('templates', function(){
  return gulp.src('app/**/**.html')
     .pipe(tc('templates.js', {
        module: 'shop',
        root: '/app'
     }))
     .pipe(gulp.dest(DEST));
});

var translations = [
'app/i18n/locale-en.json',
'app/i18n/locale-mk.json'
];

gulp.task('translation-copy', function(){
  return gulp.src(translations)
      .pipe(gulp.dest(DEST));
});

gulp.task('default',['concat-css', 'concat-libs', 'concat-app', 'templates', 'translation-copy']);

gulp.task('p', function() {
  return gulp.src(APP)
    .pipe(jspretty({
      indent_size: 2
    }))
    .pipe(gulp.dest('app'));
});

