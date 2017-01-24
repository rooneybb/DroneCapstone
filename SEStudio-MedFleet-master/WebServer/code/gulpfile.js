'use strict';

var gulp = require('gulp'),
	debug = require('gulp-debug'),
	inject = require('gulp-inject'),
	tsc = require('gulp-typescript'),
	tslint = require('gulp-tslint'),
	sourcemaps = require('gulp-sourcemaps'),
	del = require('del'),
	browserSync = require('browser-sync'),
	superstatic = require('superstatic'),
	nodemon = require('gulp-nodemon'),
	livereload = require('gulp-livereload'),
	notify = require('gulp-notify'),
	install = require("gulp-install"),
	gulpTypings = require("gulp-typings");


var binJSGlob = [
	'bin/client/**/*.js', // path to all JS files auto gen'd by editor
	'bin/client/**/*.js.map', // path to all sourcemap files auto gen'd by editor
	'bin/server/**/*.js', // path to all JS files auto gen'd by editor
	'bin/server/**/*.js.map', // path to all sourcemap files auto gen'd by editor
	'!bin/lib'
];

var ClinetJSGlob = [
	'client/**/*.js', // path to all JS files auto gen'd by editor
	'client/**/*.js.map', // path to all sourcemap files auto gen'd by editor
	'server/**/*.js', // path to all JS files auto gen'd by editor
	'server/**/*.js.map' // path to all sourcemap files auto gen'd by editor
];

var libSourceFiles = [
	'node_modules/es6-shim/es6-shim.min.js',
	'node_modules/systemjs/dist/system-polyfills.js',
	'node_modules/angular2/es6/dev/src/testing/shims_for_IE.js',
	'node_modules/angular2/bundles/angular2-polyfills.js',
	'node_modules/systemjs/dist/system.src.js',
	'node_modules/rxjs/bundles/Rx.js',
	'node_modules/angular2/bundles/angular2.dev.js',
	'node_modules/angular2/bundles/router.dev.js',
	'node_modules/angular2/bundles/http.dev.js',
	'node_modules/socket.io-client/socket.io.js'
];

var binTsFileGlob = [
	'bin/client/**/*.ts', // path to all JS files auto gen'd by editor
	'bin/client/**/*.ts.map', // path to all sourcemap files auto gen'd by editor
	'bin/server/**/*.ts', // path to all JS files auto gen'd by editor
	'bin/server/**/*.ts.map', // path to all sourcemap files auto gen'd by editor
	'!bin/lib'
];

var TsFileGlob = [
	'client/**/*.ts', // path to all JS files auto gen'd by editor
	'client/**/*.ts.map', // path to all sourcemap files auto gen'd by editor
	'server/**/*.ts', // path to all JS files auto gen'd by editor
	'server/**/*.ts.map', // path to all sourcemap files auto gen'd by editor
	'!/typings'
];
var ClientTsFileGlob = [
	'client/**/*.ts', // path to all JS files auto gen'd by editor
	'client/**/*.ts.map', // path to all sourcemap files auto gen'd by editor
	'!/typings'
];
var ServerTsFileGlob = [
	'server/**/*.ts', // path to all JS files auto gen'd by editor
	'server/**/*.ts.map', // path to all sourcemap files auto gen'd by editor
	'!/typings'
];

var clientNotTsGlob = 'client/**/!(*.ts)';
var serverNotTsGlob = 'server/**/!(*.ts)';
var publicGlob = 'public/**/*';
var binClient = 'bin/client';
var binServer = 'bin/server';
var binPublic = 'bin/public';


///////////// installs ////////////////////////

gulp.task("npm-install", function () {
	return gulp.src(['./package.json'])
  		.pipe(install());
});
gulp.task("typings-install",function(){
    var stream = gulp.src("./typings.json")
        .pipe(gulpTypings())
		.pipe(gulp.dest('client/typings'));
    return stream; // by returning stream gulp can listen to events from the stream and knows when it is finished.
});
gulp.task('copy-typings-client', function (cb) {
	return gulp.src(['typings/**/*'], { base: 'typings' })
		.pipe(gulp.dest('client/typings'));
});
gulp.task('copy-typings-server', function (cb) {
	return gulp.src(['typings/**/*'], { base: 'typings' })
		.pipe(gulp.dest('server/typings'));
});


//// Compile /////////////////////////
gulp.task('compile-client', function () {
	//var result = tsClientConfig.src()
	//	.pipe(tsc(tsClientConfig));
	//return result.js.pipe(gulp.dest(binClient))
});

gulp.task('compile-server', function () {
	// var result = tsServerConfig.src()
	// 	.pipe(tsc(tsServerConfig));
	// return result.js.pipe(gulp.dest(binServer))
});

//// Copy Static /////////////////////////
gulp.task('copy-server-static', function (cb) {
	return gulp.src([serverNotTsGlob], { base: 'server' })
		.pipe(gulp.dest(binServer));
});

gulp.task('copy-client-static', function (cb) {
	return gulp.src([clientNotTsGlob], { base: 'client' })
		.pipe(gulp.dest(binClient));
});

gulp.task('copy-public', function (cb) {
	return gulp.src([publicGlob], { base: 'public' })
        .pipe(gulp.dest(binPublic));
});


/// delte folder
gulp.task('delete-bin', function (cb) {
    return del(['bin/**/*'], cb);
})

gulp.task('copy',   ['copy-public', 'copy-server-static', 'copy-client-static']);
gulp.task('build',  ['copy-public', 'compile-client', 'compile-server', 'copy-server-static', 'copy-client-static']);
gulp.task('clean',  ['delete-bin', 'copy-public', 'copy-server-static', 'copy-client-static']);
gulp.task('install', ['npm-install', 'typings-install']);


gulp.task('ts-lint', function () {
    return gulp.src(TsFileGlob).pipe(tslint()).pipe(tslint.report('prose'));
});

gulp.task('demon', function () {
	nodemon({
		script: 'bin/server/server.js',
		ext: 'js html'
	}).on('start', ['watch'])
		.on('change', ['watch'])
		.on('restart', function () {
			console.log('restarted!');
		});
});


gulp.task('watch', function () {
	gulp.watch(serverNotTsGlob, ['copy-server-static']);
	gulp.watch(clientNotTsGlob, ['copy-client-static']);
	gulp.watch(publicGlob, 		['copy-public']);
});


gulp.task('watch-static', function () {
	gulp.watch(serverNotTsGlob, ['copy-server-static']);
	gulp.watch(clientNotTsGlob, ['copy-client-static']);
	gulp.watch(publicGlob, ['copy-public']);
});


gulp.task('default', ['watch']);
