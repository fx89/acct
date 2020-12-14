var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var __values = (this && this.__values) || function (o) {
    var m = typeof Symbol === "function" && o[Symbol.iterator], i = 0;
    if (m) return m.call(o);
    return {
        next: function () {
            if (o && i >= o.length) o = void 0;
            return { value: o && o[i++], done: !o };
        }
    };
};
(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"], {
        /***/ "./$$_lazy_route_resource lazy recursive": 
        /*!******************************************************!*\
          !*** ./$$_lazy_route_resource lazy namespace object ***!
          \******************************************************/
        /*! no static exports found */
        /***/ (function (module, exports) {
            function webpackEmptyAsyncContext(req) {
                // Here Promise.resolve().then() is used instead of new Promise() to prevent
                // uncaught exception popping up in devtools
                return Promise.resolve().then(function () {
                    var e = new Error("Cannot find module '" + req + "'");
                    e.code = 'MODULE_NOT_FOUND';
                    throw e;
                });
            }
            webpackEmptyAsyncContext.keys = function () { return []; };
            webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
            module.exports = webpackEmptyAsyncContext;
            webpackEmptyAsyncContext.id = "./$$_lazy_route_resource lazy recursive";
            /***/ 
        }),
        /***/ "./node_modules/moment/locale sync recursive ^\\.\\/.*$": 
        /*!**************************************************!*\
          !*** ./node_modules/moment/locale sync ^\.\/.*$ ***!
          \**************************************************/
        /*! no static exports found */
        /***/ (function (module, exports, __webpack_require__) {
            var map = {
                "./af": "./node_modules/moment/locale/af.js",
                "./af.js": "./node_modules/moment/locale/af.js",
                "./ar": "./node_modules/moment/locale/ar.js",
                "./ar-dz": "./node_modules/moment/locale/ar-dz.js",
                "./ar-dz.js": "./node_modules/moment/locale/ar-dz.js",
                "./ar-kw": "./node_modules/moment/locale/ar-kw.js",
                "./ar-kw.js": "./node_modules/moment/locale/ar-kw.js",
                "./ar-ly": "./node_modules/moment/locale/ar-ly.js",
                "./ar-ly.js": "./node_modules/moment/locale/ar-ly.js",
                "./ar-ma": "./node_modules/moment/locale/ar-ma.js",
                "./ar-ma.js": "./node_modules/moment/locale/ar-ma.js",
                "./ar-sa": "./node_modules/moment/locale/ar-sa.js",
                "./ar-sa.js": "./node_modules/moment/locale/ar-sa.js",
                "./ar-tn": "./node_modules/moment/locale/ar-tn.js",
                "./ar-tn.js": "./node_modules/moment/locale/ar-tn.js",
                "./ar.js": "./node_modules/moment/locale/ar.js",
                "./az": "./node_modules/moment/locale/az.js",
                "./az.js": "./node_modules/moment/locale/az.js",
                "./be": "./node_modules/moment/locale/be.js",
                "./be.js": "./node_modules/moment/locale/be.js",
                "./bg": "./node_modules/moment/locale/bg.js",
                "./bg.js": "./node_modules/moment/locale/bg.js",
                "./bm": "./node_modules/moment/locale/bm.js",
                "./bm.js": "./node_modules/moment/locale/bm.js",
                "./bn": "./node_modules/moment/locale/bn.js",
                "./bn.js": "./node_modules/moment/locale/bn.js",
                "./bo": "./node_modules/moment/locale/bo.js",
                "./bo.js": "./node_modules/moment/locale/bo.js",
                "./br": "./node_modules/moment/locale/br.js",
                "./br.js": "./node_modules/moment/locale/br.js",
                "./bs": "./node_modules/moment/locale/bs.js",
                "./bs.js": "./node_modules/moment/locale/bs.js",
                "./ca": "./node_modules/moment/locale/ca.js",
                "./ca.js": "./node_modules/moment/locale/ca.js",
                "./cs": "./node_modules/moment/locale/cs.js",
                "./cs.js": "./node_modules/moment/locale/cs.js",
                "./cv": "./node_modules/moment/locale/cv.js",
                "./cv.js": "./node_modules/moment/locale/cv.js",
                "./cy": "./node_modules/moment/locale/cy.js",
                "./cy.js": "./node_modules/moment/locale/cy.js",
                "./da": "./node_modules/moment/locale/da.js",
                "./da.js": "./node_modules/moment/locale/da.js",
                "./de": "./node_modules/moment/locale/de.js",
                "./de-at": "./node_modules/moment/locale/de-at.js",
                "./de-at.js": "./node_modules/moment/locale/de-at.js",
                "./de-ch": "./node_modules/moment/locale/de-ch.js",
                "./de-ch.js": "./node_modules/moment/locale/de-ch.js",
                "./de.js": "./node_modules/moment/locale/de.js",
                "./dv": "./node_modules/moment/locale/dv.js",
                "./dv.js": "./node_modules/moment/locale/dv.js",
                "./el": "./node_modules/moment/locale/el.js",
                "./el.js": "./node_modules/moment/locale/el.js",
                "./en-SG": "./node_modules/moment/locale/en-SG.js",
                "./en-SG.js": "./node_modules/moment/locale/en-SG.js",
                "./en-au": "./node_modules/moment/locale/en-au.js",
                "./en-au.js": "./node_modules/moment/locale/en-au.js",
                "./en-ca": "./node_modules/moment/locale/en-ca.js",
                "./en-ca.js": "./node_modules/moment/locale/en-ca.js",
                "./en-gb": "./node_modules/moment/locale/en-gb.js",
                "./en-gb.js": "./node_modules/moment/locale/en-gb.js",
                "./en-ie": "./node_modules/moment/locale/en-ie.js",
                "./en-ie.js": "./node_modules/moment/locale/en-ie.js",
                "./en-il": "./node_modules/moment/locale/en-il.js",
                "./en-il.js": "./node_modules/moment/locale/en-il.js",
                "./en-nz": "./node_modules/moment/locale/en-nz.js",
                "./en-nz.js": "./node_modules/moment/locale/en-nz.js",
                "./eo": "./node_modules/moment/locale/eo.js",
                "./eo.js": "./node_modules/moment/locale/eo.js",
                "./es": "./node_modules/moment/locale/es.js",
                "./es-do": "./node_modules/moment/locale/es-do.js",
                "./es-do.js": "./node_modules/moment/locale/es-do.js",
                "./es-us": "./node_modules/moment/locale/es-us.js",
                "./es-us.js": "./node_modules/moment/locale/es-us.js",
                "./es.js": "./node_modules/moment/locale/es.js",
                "./et": "./node_modules/moment/locale/et.js",
                "./et.js": "./node_modules/moment/locale/et.js",
                "./eu": "./node_modules/moment/locale/eu.js",
                "./eu.js": "./node_modules/moment/locale/eu.js",
                "./fa": "./node_modules/moment/locale/fa.js",
                "./fa.js": "./node_modules/moment/locale/fa.js",
                "./fi": "./node_modules/moment/locale/fi.js",
                "./fi.js": "./node_modules/moment/locale/fi.js",
                "./fo": "./node_modules/moment/locale/fo.js",
                "./fo.js": "./node_modules/moment/locale/fo.js",
                "./fr": "./node_modules/moment/locale/fr.js",
                "./fr-ca": "./node_modules/moment/locale/fr-ca.js",
                "./fr-ca.js": "./node_modules/moment/locale/fr-ca.js",
                "./fr-ch": "./node_modules/moment/locale/fr-ch.js",
                "./fr-ch.js": "./node_modules/moment/locale/fr-ch.js",
                "./fr.js": "./node_modules/moment/locale/fr.js",
                "./fy": "./node_modules/moment/locale/fy.js",
                "./fy.js": "./node_modules/moment/locale/fy.js",
                "./ga": "./node_modules/moment/locale/ga.js",
                "./ga.js": "./node_modules/moment/locale/ga.js",
                "./gd": "./node_modules/moment/locale/gd.js",
                "./gd.js": "./node_modules/moment/locale/gd.js",
                "./gl": "./node_modules/moment/locale/gl.js",
                "./gl.js": "./node_modules/moment/locale/gl.js",
                "./gom-latn": "./node_modules/moment/locale/gom-latn.js",
                "./gom-latn.js": "./node_modules/moment/locale/gom-latn.js",
                "./gu": "./node_modules/moment/locale/gu.js",
                "./gu.js": "./node_modules/moment/locale/gu.js",
                "./he": "./node_modules/moment/locale/he.js",
                "./he.js": "./node_modules/moment/locale/he.js",
                "./hi": "./node_modules/moment/locale/hi.js",
                "./hi.js": "./node_modules/moment/locale/hi.js",
                "./hr": "./node_modules/moment/locale/hr.js",
                "./hr.js": "./node_modules/moment/locale/hr.js",
                "./hu": "./node_modules/moment/locale/hu.js",
                "./hu.js": "./node_modules/moment/locale/hu.js",
                "./hy-am": "./node_modules/moment/locale/hy-am.js",
                "./hy-am.js": "./node_modules/moment/locale/hy-am.js",
                "./id": "./node_modules/moment/locale/id.js",
                "./id.js": "./node_modules/moment/locale/id.js",
                "./is": "./node_modules/moment/locale/is.js",
                "./is.js": "./node_modules/moment/locale/is.js",
                "./it": "./node_modules/moment/locale/it.js",
                "./it-ch": "./node_modules/moment/locale/it-ch.js",
                "./it-ch.js": "./node_modules/moment/locale/it-ch.js",
                "./it.js": "./node_modules/moment/locale/it.js",
                "./ja": "./node_modules/moment/locale/ja.js",
                "./ja.js": "./node_modules/moment/locale/ja.js",
                "./jv": "./node_modules/moment/locale/jv.js",
                "./jv.js": "./node_modules/moment/locale/jv.js",
                "./ka": "./node_modules/moment/locale/ka.js",
                "./ka.js": "./node_modules/moment/locale/ka.js",
                "./kk": "./node_modules/moment/locale/kk.js",
                "./kk.js": "./node_modules/moment/locale/kk.js",
                "./km": "./node_modules/moment/locale/km.js",
                "./km.js": "./node_modules/moment/locale/km.js",
                "./kn": "./node_modules/moment/locale/kn.js",
                "./kn.js": "./node_modules/moment/locale/kn.js",
                "./ko": "./node_modules/moment/locale/ko.js",
                "./ko.js": "./node_modules/moment/locale/ko.js",
                "./ku": "./node_modules/moment/locale/ku.js",
                "./ku.js": "./node_modules/moment/locale/ku.js",
                "./ky": "./node_modules/moment/locale/ky.js",
                "./ky.js": "./node_modules/moment/locale/ky.js",
                "./lb": "./node_modules/moment/locale/lb.js",
                "./lb.js": "./node_modules/moment/locale/lb.js",
                "./lo": "./node_modules/moment/locale/lo.js",
                "./lo.js": "./node_modules/moment/locale/lo.js",
                "./lt": "./node_modules/moment/locale/lt.js",
                "./lt.js": "./node_modules/moment/locale/lt.js",
                "./lv": "./node_modules/moment/locale/lv.js",
                "./lv.js": "./node_modules/moment/locale/lv.js",
                "./me": "./node_modules/moment/locale/me.js",
                "./me.js": "./node_modules/moment/locale/me.js",
                "./mi": "./node_modules/moment/locale/mi.js",
                "./mi.js": "./node_modules/moment/locale/mi.js",
                "./mk": "./node_modules/moment/locale/mk.js",
                "./mk.js": "./node_modules/moment/locale/mk.js",
                "./ml": "./node_modules/moment/locale/ml.js",
                "./ml.js": "./node_modules/moment/locale/ml.js",
                "./mn": "./node_modules/moment/locale/mn.js",
                "./mn.js": "./node_modules/moment/locale/mn.js",
                "./mr": "./node_modules/moment/locale/mr.js",
                "./mr.js": "./node_modules/moment/locale/mr.js",
                "./ms": "./node_modules/moment/locale/ms.js",
                "./ms-my": "./node_modules/moment/locale/ms-my.js",
                "./ms-my.js": "./node_modules/moment/locale/ms-my.js",
                "./ms.js": "./node_modules/moment/locale/ms.js",
                "./mt": "./node_modules/moment/locale/mt.js",
                "./mt.js": "./node_modules/moment/locale/mt.js",
                "./my": "./node_modules/moment/locale/my.js",
                "./my.js": "./node_modules/moment/locale/my.js",
                "./nb": "./node_modules/moment/locale/nb.js",
                "./nb.js": "./node_modules/moment/locale/nb.js",
                "./ne": "./node_modules/moment/locale/ne.js",
                "./ne.js": "./node_modules/moment/locale/ne.js",
                "./nl": "./node_modules/moment/locale/nl.js",
                "./nl-be": "./node_modules/moment/locale/nl-be.js",
                "./nl-be.js": "./node_modules/moment/locale/nl-be.js",
                "./nl.js": "./node_modules/moment/locale/nl.js",
                "./nn": "./node_modules/moment/locale/nn.js",
                "./nn.js": "./node_modules/moment/locale/nn.js",
                "./pa-in": "./node_modules/moment/locale/pa-in.js",
                "./pa-in.js": "./node_modules/moment/locale/pa-in.js",
                "./pl": "./node_modules/moment/locale/pl.js",
                "./pl.js": "./node_modules/moment/locale/pl.js",
                "./pt": "./node_modules/moment/locale/pt.js",
                "./pt-br": "./node_modules/moment/locale/pt-br.js",
                "./pt-br.js": "./node_modules/moment/locale/pt-br.js",
                "./pt.js": "./node_modules/moment/locale/pt.js",
                "./ro": "./node_modules/moment/locale/ro.js",
                "./ro.js": "./node_modules/moment/locale/ro.js",
                "./ru": "./node_modules/moment/locale/ru.js",
                "./ru.js": "./node_modules/moment/locale/ru.js",
                "./sd": "./node_modules/moment/locale/sd.js",
                "./sd.js": "./node_modules/moment/locale/sd.js",
                "./se": "./node_modules/moment/locale/se.js",
                "./se.js": "./node_modules/moment/locale/se.js",
                "./si": "./node_modules/moment/locale/si.js",
                "./si.js": "./node_modules/moment/locale/si.js",
                "./sk": "./node_modules/moment/locale/sk.js",
                "./sk.js": "./node_modules/moment/locale/sk.js",
                "./sl": "./node_modules/moment/locale/sl.js",
                "./sl.js": "./node_modules/moment/locale/sl.js",
                "./sq": "./node_modules/moment/locale/sq.js",
                "./sq.js": "./node_modules/moment/locale/sq.js",
                "./sr": "./node_modules/moment/locale/sr.js",
                "./sr-cyrl": "./node_modules/moment/locale/sr-cyrl.js",
                "./sr-cyrl.js": "./node_modules/moment/locale/sr-cyrl.js",
                "./sr.js": "./node_modules/moment/locale/sr.js",
                "./ss": "./node_modules/moment/locale/ss.js",
                "./ss.js": "./node_modules/moment/locale/ss.js",
                "./sv": "./node_modules/moment/locale/sv.js",
                "./sv.js": "./node_modules/moment/locale/sv.js",
                "./sw": "./node_modules/moment/locale/sw.js",
                "./sw.js": "./node_modules/moment/locale/sw.js",
                "./ta": "./node_modules/moment/locale/ta.js",
                "./ta.js": "./node_modules/moment/locale/ta.js",
                "./te": "./node_modules/moment/locale/te.js",
                "./te.js": "./node_modules/moment/locale/te.js",
                "./tet": "./node_modules/moment/locale/tet.js",
                "./tet.js": "./node_modules/moment/locale/tet.js",
                "./tg": "./node_modules/moment/locale/tg.js",
                "./tg.js": "./node_modules/moment/locale/tg.js",
                "./th": "./node_modules/moment/locale/th.js",
                "./th.js": "./node_modules/moment/locale/th.js",
                "./tl-ph": "./node_modules/moment/locale/tl-ph.js",
                "./tl-ph.js": "./node_modules/moment/locale/tl-ph.js",
                "./tlh": "./node_modules/moment/locale/tlh.js",
                "./tlh.js": "./node_modules/moment/locale/tlh.js",
                "./tr": "./node_modules/moment/locale/tr.js",
                "./tr.js": "./node_modules/moment/locale/tr.js",
                "./tzl": "./node_modules/moment/locale/tzl.js",
                "./tzl.js": "./node_modules/moment/locale/tzl.js",
                "./tzm": "./node_modules/moment/locale/tzm.js",
                "./tzm-latn": "./node_modules/moment/locale/tzm-latn.js",
                "./tzm-latn.js": "./node_modules/moment/locale/tzm-latn.js",
                "./tzm.js": "./node_modules/moment/locale/tzm.js",
                "./ug-cn": "./node_modules/moment/locale/ug-cn.js",
                "./ug-cn.js": "./node_modules/moment/locale/ug-cn.js",
                "./uk": "./node_modules/moment/locale/uk.js",
                "./uk.js": "./node_modules/moment/locale/uk.js",
                "./ur": "./node_modules/moment/locale/ur.js",
                "./ur.js": "./node_modules/moment/locale/ur.js",
                "./uz": "./node_modules/moment/locale/uz.js",
                "./uz-latn": "./node_modules/moment/locale/uz-latn.js",
                "./uz-latn.js": "./node_modules/moment/locale/uz-latn.js",
                "./uz.js": "./node_modules/moment/locale/uz.js",
                "./vi": "./node_modules/moment/locale/vi.js",
                "./vi.js": "./node_modules/moment/locale/vi.js",
                "./x-pseudo": "./node_modules/moment/locale/x-pseudo.js",
                "./x-pseudo.js": "./node_modules/moment/locale/x-pseudo.js",
                "./yo": "./node_modules/moment/locale/yo.js",
                "./yo.js": "./node_modules/moment/locale/yo.js",
                "./zh-cn": "./node_modules/moment/locale/zh-cn.js",
                "./zh-cn.js": "./node_modules/moment/locale/zh-cn.js",
                "./zh-hk": "./node_modules/moment/locale/zh-hk.js",
                "./zh-hk.js": "./node_modules/moment/locale/zh-hk.js",
                "./zh-tw": "./node_modules/moment/locale/zh-tw.js",
                "./zh-tw.js": "./node_modules/moment/locale/zh-tw.js"
            };
            function webpackContext(req) {
                var id = webpackContextResolve(req);
                return __webpack_require__(id);
            }
            function webpackContextResolve(req) {
                if (!__webpack_require__.o(map, req)) {
                    var e = new Error("Cannot find module '" + req + "'");
                    e.code = 'MODULE_NOT_FOUND';
                    throw e;
                }
                return map[req];
            }
            webpackContext.keys = function webpackContextKeys() {
                return Object.keys(map);
            };
            webpackContext.resolve = webpackContextResolve;
            module.exports = webpackContext;
            webpackContext.id = "./node_modules/moment/locale sync recursive ^\\.\\/.*$";
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/app.component.html": 
        /*!**************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/app.component.html ***!
          \**************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n<div class=\"border\">\n    <div class=\"section-title\">\n        {{ navigation.currentSection.name }}\n    </div>\n\n    <div class=\"paper\">\n        <!-- Sections defined in the NavigationService -->\n        <app-section-main             *ngIf=\"navigation.currentSection.id == navigation.APP_SECTION_MAIN\"          ></app-section-main>\n        <app-section-dashboard        *ngIf=\"navigation.currentSection.id == navigation.APP_SECTION_DASHBOARD\"     ></app-section-dashboard>\n        <app-section-accounts         *ngIf=\"navigation.currentSection.id == navigation.APP_SECTION_ACCOUNTS\"      ></app-section-accounts>\n        <app-section-deposits         *ngIf=\"navigation.currentSection.id == navigation.APP_SECTION_DEPOSITS\"      ></app-section-deposits>\n        <app-section-exchange-rates   *ngIf=\"navigation.currentSection.id == navigation.APP_SECTION_EXCHANGE_RATES\"></app-section-exchange-rates>\n    </div>\n\n\n\n    <!-- ================================================================================================= -->\n\n\n\n    <app-sliding-menu></app-sliding-menu> <!-- Keep this one at the bottom or set the z-index very high up -->\n</div>\n");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/account-record/account-record.component.html": 
        /*!***************************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/account-record/account-record.component.html ***!
          \***************************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<!-- Delete button wannabe -->\n<div\n    class=\"account-record-cell account-record-delete-button\"\n    style=\"float:left;\"\n    (click)=\"state.deleteAccountRecord(rec)\"\n>\n\n</div>\n\n<!-- Record date -->\n<div\n    class=\"account-record-cell account-record-list-record-date\"\n    style=\"float:left;\"\n    (click)=\"state.selectAccountRecord(rec);\"\n>\n    {{rec.date | date:\"yyyy-MM-dd\"}}\n</div>\n\n<!-- Income or Expense item -->\n<div\n    class=\"account-record-cell account-record-list-record-income-or-expense-item\"\n    style=\"float:left;\"\n    (click)=\"state.selectAccountRecord(rec);\"\n>\n    {{getIncomeOrExpenseItemCategoryName(rec.incomeOrExpenseItemId)}} - {{getIncomeOrExpenseItemName(rec.incomeOrExpenseItemId)}}\n</div>\n\n<!-- Record value -->\n<div\n    class=\"account-record-cell account-record-list-record-value {{rec.value < 0 ? 'account-record-list-record-value-red' : 'account-record-list-record-value-green'}}\"\n    style=\"float:left;\"\n    (click)=\"state.selectAccountRecord(rec);\"\n>\n    {{rec.value | currency:'USD':'':'2.2-2' }}\n</div>\n\n");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/account-records-input-form/account-records-input-form.component.html": 
        /*!***************************************************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/account-records-input-form/account-records-input-form.component.html ***!
          \***************************************************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<div class=\"account-records-input-form\">\n    <div class=\"account-records-input-group account-record-input-group-date\">\n        <label for=\"dtPick\">Date</label> <br />\n\n        <input\n            type=\"text\"\n            class=\"app-control-component account-record-input account-record-input-date\"\n            [matDatepicker]=\"acctRecDtPick\"\n            readonly=\"true\"\n            [(ngModel)]=\"selectedAccountRecordDateStr\"\n        >\n        <mat-datepicker-toggle matSuffix [for]=\"acctRecDtPick\"></mat-datepicker-toggle>\n        <mat-datepicker id=\"acctRecDtPick\" #acctRecDtPick></mat-datepicker>\n    </div>\n\n    <div class=\"account-records-input-group account-record-input-group-cat\">\n        <label for=\"ioeCat\">Input or Expense Category</label> <br />\n        <select\n            id=\"ioeCat\"\n            *ngIf=\"state.incomeOrExpenseItems\"\n            [(ngModel)]=\"selectedIncomeOrExpenseItem\"\n            class=\"app-control-component app-accounts-list\"\n            style=\"width: calc(100% - 80px); float: left;\"\n            (change)=\"onItemChange($event)\"\n        >\n            <option *ngFor=\"let item of state.incomeOrExpenseItems\"\n                [ngValue]=\"item\"\n                [selected]=\"item && selectedIncomeOrExpenseItem && item.id == selectedIncomeOrExpenseItem.id\"\n            >\n                {{ item ? getCatItemName(item) : 'select one' }}\n            </option>\n        </select>\n\n        <div\n          class=\"app-control-component app-button input-form-categories-edit-button\"\n          (click)=\"showIncomeOrExpenseItemsManager()\"\n        ></div>\n    </div>\n\n    <div class=\"account-records-input-group account-record-input-group-value\">\n        <label for=\"vaInput\">Value</label> <br />\n        <input  id=\"vaInput\"\n                class=\"app-control-component account-record-input\"\n                type=\"text\" [(ngModel)]=\"state.selectedAccountRecord.value\"\n                (keyup)=\"onValueKeyUp($event)\"\n        >\n    </div>\n</div>\n");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/account-records/account-records.component.html": 
        /*!*****************************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/account-records/account-records.component.html ***!
          \*****************************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<div class=\"records-list-body acount-records-list\">\n    <div\n      *ngFor=\"let rec of state.selectedAccountRecords\"\n      class=\"records-list-record acount-records-list-record {{ (state.selectedAccountRecord && state.selectedAccountRecord.id) == rec.id ? 'records-list-record-selected' : ' ' }}\"\n    >\n        <app-account-record [rec]=\"rec\"></app-account-record>\n    </div>\n</div>");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/account-summary/account-summary.component.html": 
        /*!*****************************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/account-summary/account-summary.component.html ***!
          \*****************************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<table><tr><td></td><td>\n    <table>\n        <tr><td>Name     </td><td>&nbsp; </td><td style=\"text-align:right;\">{{   account.name }}                       </td></tr>\n        <tr><td>Incoming </td><td>&nbsp; </td><td style=\"text-align:right;\">{{   summary.runningSumIncoming | currency:'USD':'':'2.2-2'}}  </td></tr>\n        <tr><td>Outgoing </td><td>&nbsp; </td><td style=\"text-align:right;\">{{ - summary.runningSumOutgoing | currency:'USD':'':'2.2-2'}}  </td></tr>\n        <tr><td>Balance  </td><td>&nbsp; </td><td style=\"text-align:right;\">{{   summary.runningSumIncoming + summary.runningSumOutgoing | currency:'USD':'':'2.2-2'}}  </td></tr>\n    </table>\n</td></tr></table>");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/accounts-list/accounts-list.component.html": 
        /*!*************************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/accounts-list/accounts-list.component.html ***!
          \*************************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n<select class=\"app-control-component app-accounts-list\" #t (change)=\"selectAccountId(t.value)\">\n    <option *ngFor=\"let account of state.accounts\"\n            value=\"{{account.id}}\"\n            [selected]=\"account.id == state.selectedAccountId\"> {{ account.name }}\n    </option>\n</select>\n");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/accounts-manager/accounts-manager.component.html": 
        /*!*******************************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/accounts-manager/accounts-manager.component.html ***!
          \*******************************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<div class=\"generic-panel-title app-control-component\">Edit Accounts</div>\n\n<div>\n<app-editable-names-list\n    *ngIf=\"state.accounts\"\n    [(listItems)]=\"state.accounts\"\n    [(displayValueCallback)]=\"getItemName\"\n    [(newItemCallback)]=\"newItem\"\n    (onSelect)=\"selectItem($event)\"\n    (onAdd)=\"addItem($event)\"\n    (onDelete)=\"deleteItem($event)\"\n    (onEditBegin)=\"beginEditItem($event)\"\n    (onEditEnd)=\"endEditItem($event)\"\n></app-editable-names-list>\n</div>");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/banks-list/banks-list.component.html": 
        /*!*******************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/banks-list/banks-list.component.html ***!
          \*******************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<div class=\"generic-panel-title app-control-component\">\n    Banks list\n</div>\n\n<div>\n<app-editable-names-list\n    *ngIf=\"state.banks\"\n    [(listItems)]=\"state.banks\"\n    [(displayValueCallback)]=\"getBankName\"\n    [(newItemCallback)]=\"newBank\"\n    (onSelect)=\"selectBank($event)\"\n    (onAdd)=\"addBank($event)\"\n    (onDelete)=\"deleteBank($event)\"\n    (onEditBegin)=\"beginEditBank($event)\"\n    (onEditEnd)=\"endEditBank($event)\"\n></app-editable-names-list>\n</div>");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/currencies-manager/currencies-manager.component.html": 
        /*!***********************************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/currencies-manager/currencies-manager.component.html ***!
          \***********************************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<table style=\"width:100%\">\n<tr><td>\n    <div style=\"float:top;\">\n        <div class=\"editable-names-list-container\" style=\"float: left\">\n            Supported\n            <div class=\"editable-names-list\" style=\"overflow-x: hidden; overflow-y: scroll; min-width:60px; width: 60px;\">\n                <div\n                    *ngFor=\"let item of state.supportedCurrencies\"\n                    class=\"editable-names-list-element {{ (selectedSupportedCurrency && (selectedSupportedCurrency == item)) ? 'editable-names-list-element-selected' : ' ' }}\"\n                    (click)=\"selectSupportedCurrency(item)\"\n                >\n                    {{ item }}\n                </div>\n            </div>\n        </div>\n\n        <div style=\"width:10px; float: left;\">&nbsp; </div>\n\n        <div class=\"editable-names-list-container\" style=\"float: left; width: calc(100% - 100px)\">\n            Monitored\n            <div class=\"editable-names-list\" style=\"overflow-x: hidden; overflow-y: scroll; min-width: 100%;\">\n                <div\n                    *ngFor=\"let item of state.monitoredCurrencies\"\n                    class=\"editable-names-list-element {{ (selectedMonitoredCurrency && (selectedMonitoredCurrency.id == item.id)) ? 'editable-names-list-element-selected' : ' ' }}\"\n                    (click)=\"selectMonitoredCurrency(item)\"\n                >\n                    <table><tr>\n                        <td style=\"width: 50px;\">{{ item.currencyTypeName }}</td>\n                        <td style=\"width: 70px;\">{{ item.lastCollectedDate | date:\"yyyy-MM-dd\" }}</td>\n                        <td style=\"width: 60px;\">{{ item.lastCollectedValue | currency:'USD':'':'1.4-4' }}</td>\n                </tr></table>\n                </div>\n            </div>\n        </div>\n    </div>\n\n</td></tr><tr><td>\n\n    <div class=\"editable-names-list-control-panel\" style=\"float:bottom;\">\n        <div\n            *ngIf=\"selectedSupportedCurrency\"\n            class=\"editable-names-list-button editable-names-list-button-add\"\n            (click)=\"addButtonClicked()\"\n        >\n            Add\n        </div>\n        <div\n            *ngIf=\"selectedMonitoredCurrency\"\n            class=\"editable-names-list-button editable-names-list-button-del\"\n            (click)=\"deleteButtonClicked()\"\n        >\n            Del\n        </div>\n        <div\n            *ngIf=\"!loading && state.monitoredCurrencies && state.monitoredCurrencies.length > 0\"\n            class=\"editable-names-list-button editable-names-list-button-collect\"\n            (click)=\"collectButtonClicked()\"\n        >\n            Collect\n        </div>\n        <div *ngIf=\"loading\">... loading ...</div>\n    </div>\n\n</td></tr>\n</table>");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/deposits-input-form/deposits-input-form.component.html": 
        /*!*************************************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/deposits-input-form/deposits-input-form.component.html ***!
          \*************************************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<!-- Source account -->\r\n<div style=\"float: left\">\r\n    Source account <br />\r\n    <select\r\n        [(ngModel)]=\"sourceAccount\"\r\n        class=\"app-control-component app-accounts-list\"\r\n        style=\"width: 100%;\"\r\n    >\r\n        <option *ngFor=\"let item of state.accounts\"\r\n            [ngValue]=\"item\"\r\n            [selected]=\"item && sourceAccount.id == item.id\"\r\n        >\r\n            {{ item ? item.name : 'select one' }}\r\n        </option>\r\n    </select>\r\n</div>\r\n\r\n<!-- Bank -->\r\n<div style=\"float: left\">\r\n    Bank <br />\r\n    <select\r\n        [(ngModel)]=\"bank\"\r\n        class=\"app-control-component app-accounts-list\"\r\n        style=\"width: 100%;\"\r\n    >\r\n        <option *ngFor=\"let item of state.banks\"\r\n            [ngValue]=\"item\"\r\n            [selected]=\"item && bank.id == item.id\"\r\n        >\r\n            {{ item ? item.name : 'select one' }}\r\n        </option>\r\n    </select>\r\n</div>\r\n\r\n<!-- Account number -->\r\n<div style=\"float: left\">\r\n    Account number <br />\r\n    <input\r\n        class=\"app-control-component\"\r\n        style=\"width: 100%;\"\r\n        type=\"text\"\r\n        [(ngModel)]=\"accountNumber\"\r\n        (keyup)=\"onAccountNumberFieldUp($event)\"\r\n    >\r\n</div>\r\n\r\n<div style=\"float: left\">\r\n    &nbsp; &nbsp; &nbsp; \r\n</div>\r\n\r\n<!-- Start date -->\r\n<div style=\"float: left\">\r\n    Start date <br />\r\n    <input\r\n            type=\"text\"\r\n            class=\"app-control-component account-record-input account-record-input-date\"\r\n            style=\"width: 100%;\"\r\n            [matDatepicker]=\"startDatePicker\"\r\n            readonly=\"true\"\r\n            [(ngModel)]=\"startDateStr\"\r\n    >\r\n    <mat-datepicker-toggle matSuffix [for]=\"startDatePicker\"></mat-datepicker-toggle>\r\n    <mat-datepicker id=\"startDatePicker\" #startDatePicker></mat-datepicker>\r\n</div>\r\n\r\n<!-- End date -->\r\n<div style=\"float: left\">\r\n    End date <br />\r\n    <input\r\n            type=\"text\"\r\n            class=\"app-control-component account-record-input account-record-input-date\"\r\n            style=\"width: 100%;\"\r\n            [matDatepicker]=\"endDatePicker\"\r\n            readonly=\"true\"\r\n            [(ngModel)]=\"endDateStr\"\r\n    >\r\n    <mat-datepicker-toggle matSuffix [for]=\"endDatePicker\"></mat-datepicker-toggle>\r\n    <mat-datepicker id=\"endDatePicker\" #endDatePicker></mat-datepicker>\r\n</div>\r\n\r\n<!-- Value -->\r\n<div style=\"float: left\">\r\n    Value <br />\r\n    <input\r\n        class=\"app-control-component\"\r\n        style=\"width: 50px;\"\r\n        type=\"text\"\r\n        [(ngModel)]=\"value\"\r\n        (keyup)=\"onTextFieldUp($event)\"\r\n    >\r\n</div>\r\n\r\n<!-- Interest % -->\r\n<div style=\"float: left\">\r\n    Interest % <br />\r\n    <input\r\n        class=\"app-control-component\"\r\n        style=\"width: 40px;\"\r\n        type=\"text\"\r\n        [(ngModel)]=\"interestPercent\"\r\n        (keyup)=\"onTextFieldUp($event)\"\r\n    >\r\n</div>\r\n\r\n\r\n");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/deposits-table/deposits-table.component.html": 
        /*!***************************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/deposits-table/deposits-table.component.html ***!
          \***************************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<div class=\"records-list-header\">\n    <div class=\"records-list-heder-cell dposits-source-account-column\">Source account</div>\n    <div class=\"records-list-heder-cell deposits-bank-column\">Bank</div>\n    <div class=\"records-list-heder-cell deposits-account-number-column\">Account number</div>\n    <div class=\"records-list-heder-cell deposits-start-date-column\">Start date</div>\n    <div class=\"records-list-heder-cell deposits-end-date-column\">End date</div>\n    <div class=\"records-list-heder-cell deposits-value-column\">Value</div>\n    <div class=\"records-list-heder-cell deposits-interest-percent-column\">Interest %</div>\n    <div class=\"records-list-heder-cell deposits-interest-value-column\">Interest</div>\n    <div class=\"records-list-heder-cell deposits-progress-column\">Progress</div>\n</div>\n<div\n    class=\"records-list-body acount-records-list\"\n    *ngIf=\"state.accounts && state.banks && state.deposits && state.deposits != []\"\n>\n    <div\n      *ngFor=\"let rec of state.deposits\"\n      class=\"records-list-record {{ (state.selectedDeposit && state.selectedDeposit.id == rec.id) ? 'records-list-record-selected' : ' ' }}\"\n      (click)=\"state.selectDeposit(rec)\"\n    >\n        <div class=\"records-list-body-cell dposits-source-account-column\">{{ getSourceAccountName(rec) }}</div>\n        <div class=\"records-list-body-cell deposits-bank-column\">{{ getBankName(rec) }}</div>\n        <div class=\"records-list-body-cell deposits-account-number-column\">{{ rec.accountNumber }}</div>\n        <div class=\"records-list-body-cell deposits-start-date-column\">{{ rec.startDate | date:\"yyyy-MM-dd\" }}</div>\n        <div class=\"records-list-body-cell deposits-end-date-column\">{{ rec.endDate | date:\"yyyy-MM-dd\" }}</div>\n        <div class=\"records-list-body-cell deposits-value-column\">{{ rec.value | currency:'USD':'':'2.2-2' }}</div>\n        <div class=\"records-list-body-cell deposits-interest-percent-column\">{{ rec.interestPercent | currency:'USD':'':'2.2-2' }}%</div>\n        <div class=\"records-list-body-cell deposits-interest-value-column\">{{ (rec.value * rec.interestPercent / 100) | currency:'USD':'':'2.2-2' }}</div>\n\n        <div class=\"records-list-body-cell deposits-progress-column\">\n            <app-progress-bar [valueFromZeroToOneHundred]=\"computeProgress(rec)\"></app-progress-bar>\n        </div>\n    </div>\n</div>");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/dialog-error/dialog-error.component.html": 
        /*!***********************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/dialog-error/dialog-error.component.html ***!
          \***********************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<div class=\"dialog-background\" style=\"width:100%\">\n    <p>{{ dialogService.dialogMessage }}</p>\n\n    <div class=\"app-control-component app-button dialogue-button\" (click)=\"dialogService.closeErrDialog()\">Close</div>\n</div>\n");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/dialog-yes-no/dialog-yes-no.component.html": 
        /*!*************************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/dialog-yes-no/dialog-yes-no.component.html ***!
          \*************************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<div class=\"dialog-background\" style=\"width:100%\">\n    <p>{{ dialogService.dialogMessage }}</p>\n\n    <br />\n\n    <table style=\"width: 100%\"><tr><td style=\"width: 50%; text-align: center;\">\n        <div class=\"app-control-component app-button dialogue-button\" (click)=\"dialogService.closeYesNoDialog(true)\">Yes</div>\n    </td><td style=\"width: 50%; text-align: center;\">\n        <div class=\"app-control-component app-button dialogue-button\" (click)=\"dialogService.closeYesNoDialog(false)\">No</div>\n    </td></tr></table>\n</div>\n");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/editable-names-list/editable-names-list.component.html": 
        /*!*************************************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/editable-names-list/editable-names-list.component.html ***!
          \*************************************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n<div class=\"editable-names-list-container\">\n    <div class=\"editable-names-list\" style=\"overflow-x: hidden; overflow-y: scroll;\">\n        <div\n            *ngFor=\"let item of listItems\"\n            class=\"editable-names-list-element {{ (selectedItem && (selectedItem == item)) ? 'editable-names-list-element-selected' : ' ' }}\"\n            (click)=\"selectItem(item)\"\n        >\n            {{ displayValueCallback(item) }}\n        </div>\n    </div>\n    <div class=\"editable-names-list-control-panel\">\n        <div\n            *ngIf=\"!selectedItemDisplatValue\"\n            class=\"editable-names-list-button editable-names-list-button-add\"\n            (click)=\"addButtonClicked()\"\n        >\n            Add\n        </div>\n        <div\n            *ngIf=\"selectedItem && !selectedItemDisplatValue\"\n            class=\"editable-names-list-button editable-names-list-button-edit\"\n            (click)=\"editButtonClicked()\"\n        >\n            Edt\n        </div>\n        <div\n            *ngIf=\"selectedItem && !selectedItemDisplatValue\"\n            class=\"editable-names-list-button editable-names-list-button-del\"\n            (click)=\"deleteButtonClicked()\"\n        >\n            Del\n        </div>\n\n        <input\n            *ngIf=\"selectedItemDisplatValue\"\n            class=\"app-control-component editable-names-list-name-input\"\n            type=\"text\" [(ngModel)]=\"selectedItemDisplatValue\"\n            (keyup)=\"onEditInputKeyUp($event)\"\n        >\n    </div>\n</div>");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/income-or-expense-category-select/income-or-expense-category-select.component.html": 
        /*!*****************************************************************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/income-or-expense-category-select/income-or-expense-category-select.component.html ***!
          \*****************************************************************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n<select *ngIf=\"state.incomeOrExpenseItems\"\n        [(ngModel)]=\"selectedIncomeOrExpenseItemObj\"\n        class=\"app-control-component app-accounts-list {{cssClassName}}\"\n        (change)=\"onChange()\"\n>\n    <option *ngFor=\"let item of state.incomeOrExpenseItems\"\n            [ngValue]=\"item\"\n            [selected]=\"item && selectedIncomeOrExpenseItemObj.id == item.id\"\n    >\n        {{ item ? getCatItemName(item) : 'select one' }}\n    </option>\n</select>\n");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/income-or-expense-items-manager/income-or-expense-items-manager.component.html": 
        /*!*************************************************************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/income-or-expense-items-manager/income-or-expense-items-manager.component.html ***!
          \*************************************************************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<!-- Income or expense item categories selector -->\n<select\n    *ngIf=\"state.incomeOrExpenseItemCategories && !allowEditCategories\"\n    [(ngModel)]=\"state.selectedIncomeOrExpenseItemCategory\"\n    class=\"app-control-component app-accounts-list\"\n    style=\"width: 100%\"\n    (change)=\"loadIncomeOrExpenseItemsForCategory()\"\n>\n    <option *ngFor=\"let item of state.incomeOrExpenseItemCategories\"\n        [ngValue]=\"item\"\n        [selected]=\"item && state.selectedIncomeOrExpenseItemCategory && item.id == state.selectedIncomeOrExpenseItemCategory.id\"\n    >\n        {{ item ? item.name : 'select one' }}\n    </option>\n</select>\n\n\n<app-editable-names-list\n    *ngIf=\"state.incomeOrExpenseItemCategories && allowEditCategories\"\n    [(listItems)]=\"state.incomeOrExpenseItemCategories\"\n    [(displayValueCallback)]=\"getItemName\"\n    [(newItemCallback)]=\"newCategory\"\n    (onSelect)=\"selectCategory($event)\"\n    (onAdd)=\"addCategory($event)\"\n    (onDelete)=\"deleteCategory($event)\"\n    (onEditBegin)=\"beginEditCategory($event)\"\n    (onEditEnd)=\"endEditCategory($event)\"\n    style=\"float: left; width: 200px;\"\n></app-editable-names-list>\n\n<app-editable-names-list\n    *ngIf=\"state.selectedIncomeOrExpenseItemCategory && incomeOrExpenseItems\"\n    [(listItems)]=\"incomeOrExpenseItems\"\n    [(displayValueCallback)]=\"getItemName\"\n    [(newItemCallback)]=\"newItem\"\n    (onSelect)=\"selectItem($event)\"\n    (onAdd)=\"addItem($event)\"\n    (onDelete)=\"deleteItem($event)\"\n    (onEditBegin)=\"beginEditItem($event)\"\n    (onEditEnd)=\"endEditItem($event)\"\n></app-editable-names-list>");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/money-transfer-form/money-transfer-form.component.html": 
        /*!*************************************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/money-transfer-form/money-transfer-form.component.html ***!
          \*************************************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<div class=\"generic-panel\" style=\"width: calc(100% - 10px); height:calc(100% - 10px);\">\n    \n    <table>\n        <tr>\n            <td>\n                From account\n            </td>\n            <td style=\"width: 10px;\"></td>\n            <td>\n                <select\n                    [(ngModel)]=\"fromAccount\"\n                    class=\"app-control-component app-accounts-list\"\n                    style=\"width: 100px;\"\n                >\n                    <option *ngFor=\"let item of state.accounts\"\n                        [ngValue]=\"item\"\n                        [selected]=\"fromAccount && item.id == fromAccount.id\"\n                    >\n                        {{ item ? item.name : 'select one' }}\n                    </option>\n                </select>\n            </td>\n        </tr><tr>\n            <td>\n                To account\n            </td>\n            <td></td>\n            <td>\n                <select\n                    [(ngModel)]=\"toAccount\"\n                    class=\"app-control-component app-accounts-list\"\n                    style=\"width: 100px;\"\n                >\n                    <option *ngFor=\"let item of state.accounts\"\n                        [ngValue]=\"item\"\n                        [selected]=\"toAccount && item.id == toAccount.id\"\n                    >\n                        {{ item ? item.name : 'select one' }}\n                    </option>\n                </select>\n            </td>\n        </tr><tr>\n            <td>\n                Amount\n            </td>\n            <td></td>\n            <td>\n                <input\n                    class=\"app-control-component account-record-input\"\n                    type=\"text\" [(ngModel)]=\"amount\"\n                >\n            </td>\n        </tr><tr>\n            <td></td><td></td>\n            <td>\n                <div\n                    class=\"app-control-component app-button app-button-new-account\"\n                    style=\"width: 150px;\"\n                    (click)= \"startMoneyTransfer()\"\n                >\n                    Transfer\n                </div>\n            </td>\n        </tr>\n    </table>\n</div>\n\n\n\n");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/monthly-balance-report/monthly-balance-report.component.html": 
        /*!*******************************************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/monthly-balance-report/monthly-balance-report.component.html ***!
          \*******************************************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<google-chart #chart\n    [type]=\"'ComboChart'\"\n    [data]=\"chartData\"\n    [columnNames]=\"chartColumnNames\"\n    [options]=\"chartOptions\"\n></google-chart>\n");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/monthly-savings-report/monthly-savings-report.component.html": 
        /*!*******************************************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/monthly-savings-report/monthly-savings-report.component.html ***!
          \*******************************************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<google-chart #chart\n    [type]=\"chartType\"\n    [data]=\"chartData\"\n    [columnNames]=\"chartColumnNames\"\n    [options]=\"chartOptions\"\n></google-chart>\n");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/progress-bar/progress-bar.component.html": 
        /*!***********************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/progress-bar/progress-bar.component.html ***!
          \***********************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<div class=\"progress-bar-sheet\">\n    <div class=\"progress-bar-border\" style=\"float: left\">\n        <div class=\"progress-bar-full\" style=\"float: left;\" [style.width.%]=\"valueFromZeroToOneHundred\"></div>\n    </div>\n    <div class=\"progress-bar-value\">\n        {{ valueFromZeroToOneHundred  | currency:'USD':'':'2.2-2' }}%\n    </div>\n</div>\n\n");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/section-accounts/accounts.component.html": 
        /*!***********************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/section-accounts/accounts.component.html ***!
          \***********************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n<!-- ACCOUNT SELECTION BOX -->\n<div class=\"accounts-top-bar\">\n    <div style=\"float: left\">Account &nbsp;</div>\n    <app-accounts-list style=\"float:left\"></app-accounts-list>\n\n    <div\n        class=\"app-control-component app-button input-form-categories-edit-button\"\n        (click)=\"showAccountsManager()\"\n    ></div>\n</div>\n\n\n\n<!-- ACCOUNT SUMMARY -->\n<div class=\"generic-panel account-summary-panel\" style=\"float: left; \">\n    <span class=\"generic-panel-title\">Account summary:</span><br />\n    <app-account-summary\n        *ngIf=\"state.selectedAccountSummary != null && state.selectedAccount != null\"\n        [summary]=\"state.selectedAccountSummary\"\n        [account]=\"state.selectedAccount\"\n    ></app-account-summary>\n\n    <br /><br />\n\n    <b>Monthly balance report</b>\n    <app-monthly-balance-report\n        [width]=\"280\"\n        [height]=\"120\"\n        [hideDates]=\"true\"\n        [sourceDate]=\"state.monthlyBalanceReport\"\n    ></app-monthly-balance-report>\n\n    <br /><br />\n\n    <b>Monthly delta report</b>\n    <app-monthly-savings-report\n        [chartType]=\"'ColumnChart'\"\n        [width]=\"280\"\n        [height]=\"120\"\n        [hideDates]=\"true\"\n        [sourceDate]=\"state.monthlyBalanceReport\"\n        [sourceDataXValueMapper]=\"sourceXValueMapper\"\n        [sourceDataYValueMapper]=\"sourceYValueMapper\"\n        [forceMin]=\"0\"\n        [chartColumnNames]=\"['Month', 'Delta']\"\n    ></app-monthly-savings-report>\n</div>\n\n\n\n<!-- SPACER -->\n<div id=\"account-panels-spacer\" style=\"float: left;\">&nbsp;</div>\n\n\n\n<!-- ACCOUNT DETAILS AND INPUT FORM -->\n<div class=\"generic-panel account-records-panel\" style=\"float: left; \">\n    <!-- RECORDS LIST -->\n    <span class=\"generic-panel-title\">Account records:</span><br />\n    <app-account-records *ngIf=\"state.selectedAccountRecords != null && state.incomeOrExpenseItems != null\"></app-account-records>\n\n    <!-- SPACER -->\n    <div class=\"account-records-separator\"></div>\n\n    <!-- INPUT FORM -->\n    <app-account-records-input-form *ngIf=\"state.selectedAccountRecord\"></app-account-records-input-form>\n\n    <!-- NEW RECORD BUTTON -->\n    <div\n        class=\"app-control-component app-button app-button-new-account\"\n        style=\"float: left\"\n        *ngIf=\" !(state.selectedAccountRecord)\"\n        (click)= \"state.newAccountRecord()\"\n    >\n        New record\n    </div>\n\n    <!-- TRANSFER BUTTON -->\n    <div\n        class=\"app-control-component app-button app-button-new-account\"\n        style=\"float: left\"\n        *ngIf=\" !(state.selectedAccountRecord)\"\n        (click)= \"showMoneyTransferForm()\"\n    >\n        Transfer\n    </div>\n</div>\n\n");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/section-dashboard/dashboard.component.html": 
        /*!*************************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/section-dashboard/dashboard.component.html ***!
          \*************************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<!-- Status panel -->\n<div class=\"generic-panel\" style=\"width:calc(50% - 15px); height:calc(100% - 10px); float:left;\">\n    <div style=\"float: left\">\n        <span class=\"generic-panel-title\">Deposits Value</span>\n\n        <table *ngIf=\"state.monitoredCurrencies && state.totalDepositsValue\">\n            <tr *ngFor=\"let cr of state.monitoredCurrencies\">\n                <td>Deposits value in {{ cr.currencyTypeName }}</td>\n                <td> : </td>\n                <td style=\"text-align: right\">\n                    {{ state.totalDepositsValue / cr.lastCollectedValue | currency:'USD':'':'2.2-2' }}\n                </td>\n            </tr>\n        </table>\n    </div>\n\n    <div style=\"width: 50px; float: left;\">&nbsp;</div>\n\n    <div style=\"float: left\">\n        <span class=\"generic-panel-title\">Account Balance</span>\n\n        <table *ngIf=\"state.accountSummariesLoaded\">\n            <tr *ngFor=\"let acc of state.accounts\">\n                <td>{{ acc.name }}</td>\n                <td> : </td>\n                <td style=\"text-align: right\">\n                    {{ getAccountValue(acc.id) | currency:'USD':'':'2.2-2' }}\n                </td>\n            </tr>\n        </table>\n    </div>\n\n    <div style=\"float: left; width:100%; height:25px;\">&nbsp;</div>\n\n    <div *ngIf=\"state.monitoredCurrencies && state.monthlyDepositsBalanceReport\">\n        <div\n            style=\"float: left;\"\n            *ngFor=\"let cr of state.monitoredCurrencies\"\n        >\n            <b>Historical deposits value in {{cr.currencyTypeName}}</b>\n            <app-monthly-savings-report\n                [chartType]=\"'ColumnChart'\"\n                [width]=\"290\"\n                [height]=\"120\"\n                [hideDates]=\"true\"\n                [sourceDate]=\"state.monthlyDepositsBalanceReport\"\n                [sourceDataXValueMapper]=\"sourceXValueMapper\"\n                [sourceDataYValueMapper]=\"sourceYValueMappers[cr.currencyTypeName.valueOf()]\"\n                [forceMin]=\"-5\"\n                [chartColumnNames]=\"['Month', 'Value in ' + cr.currencyTypeName]\"\n            ></app-monthly-savings-report>\n        </div>\n    </div>\n</div>\n\n<!-- Spacer -->\n<div style=\"width: 10px; float: left;\">&nbsp;</div>\n\n<!-- Breakdown panel -->\n<div class=\"generic-panel\" style=\"width:calc(50% - 15px); height:calc(100% - 10px); float:left;\">\n    <div *ngIf=\"state.incomeOrExpenseItems && state.incomeOrExpenseItemCategories && state.monthlyExpensesReports && state.accounts\">\n        <span class=\"generic-panel-title\">Breakdown of Expenses</span>\n\n        <br /><br />\n\n        <div class=\"dashboard-expenses-report-controls\" style=\"width: 100%; height: 50px;  padding-top: 4px;\">\n\n            <div style=\"width: 15px; float: left;\">&nbsp;</div>\n\n            <!-- Account Select -->\n            <div style=\"float: left;\">\n                Account <br />\n                <select\n                    [(ngModel)]=\"selectedAccount\"\n                    class=\"app-control-component app-accounts-list\"\n                    style=\"width: 100px;\"\n                    (change)=\"onAccountChanged()\"\n                >\n                    <option [ngValue]=\"null\" [selected]=\"!selectedAccount\">\n                        All\n                    </option>\n                    <option *ngFor=\"let item of state.accounts\"\n                        [ngValue]=\"item\"\n                        [selected]=\"seletedAccount && item.id == seletedAccount.id\"\n                    >\n                        {{ item ? item.name : 'select one' }}\n                    </option>\n                </select>\n            </div>\n\n            <div style=\"width: 15px; float: left;\">&nbsp;</div>\n\n            <!-- Category Select -->\n            <div style=\"float: left;\">\n                Category <br />\n                <select\n                    [(ngModel)]=\"selectedCategory\"\n                    class=\"app-control-component app-accounts-list\"\n                    style=\"width: 100px;\"\n                    (change)=\"onCategoryChanged()\"\n                >\n                    <option [ngValue]=\"null\" [selected]=\"!selectedCategory\">\n                        All\n                    </option>\n                    <option *ngFor=\"let item of state.incomeOrExpenseItemCategories\"\n                        [ngValue]=\"item\"\n                        [selected]=\"selectedCategory && item.id == selectedCategory.id\"\n                    >\n                        {{ item ? item.name : 'select one' }}\n                    </option>\n                </select>\n            </div>\n\n            <div style=\"width: 15px; float: left;\">&nbsp;</div>\n\n            <!-- Item Select -->\n            <div style=\"float: left;\">\n                Item <br />\n                <select\n                    [(ngModel)]=\"selectedItem\"\n                    class=\"app-control-component app-accounts-list\"\n                    style=\"width: 100px;\"\n                    (change)=\"onItemChanged()\"\n                >\n                    <option [ngValue]=\"null\" [selected]=\"!selectedItem\">\n                        All\n                    </option>\n                    <option *ngFor=\"let item of getValidIncomeOrExpenseItems()\"\n                        [ngValue]=\"item\"\n                        [selected]=\"selectedItem && item.id == selectedItem.id\"\n                    >\n                        {{ item ? item.name : 'select one' }}\n                    </option>\n                </select>\n            </div>\n        </div>\n\n        <br /><br />\n\n        <!-- Breakdown Report Chart -->\n        <div class=\"dashboard-expenses-report-chart\" style=\"width: 100%; height: 400px; padding-top: 10px; padding-bottom: 10px;\">\n            <app-monthly-savings-report\n                *ngIf=\"monthlyExpensesReportChartData\"\n                [chartType]=\"'ColumnChart'\"\n                [width]=\"600\"\n                [height]=\"200\"\n                [hideDates]=\"false\"\n                [sourceDate]=\"monthlyExpensesReportChartData\"\n                [sourceDataXValueMapper]=\"monthlyExpensesReportChartXValueMapper\"\n                [sourceDataYValueMapper]=\"monthlyExpensesReportChartYValueMapper\"\n                [forceMin]=\"-5\"\n                [chartColumnNames]=\"['Month', 'Total Expenses']\"\n            ></app-monthly-savings-report>\n        </div>\n    </div>\n</div>");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/section-deposits/deposits.component.html": 
        /*!***********************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/section-deposits/deposits.component.html ***!
          \***********************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<!-- ACCOUNT SUMMARY -->\n<div class=\"generic-panel deposits-summary-panel\" style=\"float: left; \">\n    <span class=\"generic-panel-title\">Deposits summary:</span><br />\n    <table style=\"width: 280px;\" *ngIf=\"state.deposits\">\n        <tr><td>Number of active Deposits</td><td style=\"text-align: right;\">{{ state.deposits.length }}</td></tr>\n        <tr><td>Total deposited sum      </td><td style=\"text-align: right;\">{{ getTotalDepositsValue()    | currency:'USD':'':'2.2-2' }}</td></tr>\n        <tr><td>Total expected interest  </td><td style=\"text-align: right;\">{{ getTotalDepositsInterest() | currency:'USD':'':'2.2-2' }}</td></tr>\n    </table>\n    <br /><br />\n\n\n    <span class=\"generic-panel-title\">First deposit to reach maturity:</span><br />\n    <table style=\"width: 280px;\" *ngIf=\"state.firstDepositToReachMaturity\">\n        <tr><td>Bank account number &nbsp; &nbsp; </td><td style=\"text-align: right;\">{{ state.firstDepositToReachMaturity.accountNumber }}</td></tr>\n        <tr><td>Maturity date                     </td><td style=\"text-align: right;\">{{ state.firstDepositToReachMaturity.endDate | date:\"yyyy-MM-dd\" }}</td></tr>\n        <tr><td>Deposited value                   </td><td style=\"text-align: right;\">{{ state.firstDepositToReachMaturity.value | currency:'USD':'':'2.2-2' }}</td></tr>\n        <tr><td>Interest rate                     </td><td style=\"text-align: right;\">{{ state.firstDepositToReachMaturity.interestPercent | currency:'USD':'':'2.2-2' }}</td></tr>\n        <tr><td>Interest value                    </td><td style=\"text-align: right;\">{{ ((state.firstDepositToReachMaturity.value * state.firstDepositToReachMaturity.interestPercent) / 100) | currency:'USD':'':'2.2-2' }}</td></tr>\n    </table>\n\n    <br /><br />\n\n    <b>Monthly balance report</b>\n    <app-monthly-balance-report\n        [width]=\"280\"\n        [height]=\"120\"\n        [hideDates]=\"true\"\n        [sourceDate]=\"state.monthlyDepositsBalanceReport\"\n    ></app-monthly-balance-report>\n</div>\n\n\n\n<!-- SPACER -->\n<div id=\"account-panels-spacer\" style=\"float: left;\">&nbsp;</div>\n\n\n\n<!-- ACCOUNT DETAILS AND INPUT FORM -->\n<div class=\"generic-panel deposit-records-panel\" style=\"float: left; \">\n    <!-- DEPOSIS LIST -->\n    <span class=\"generic-panel-title\">Deposits</span><br />\n    <app-deposits-table></app-deposits-table>\n\n    <!-- SPACER -->\n    <div class=\"account-records-separator\"></div>\n\n    <!-- INPUT FORM -->\n    <app-deposits-input-form *ngIf=\"state.selectedDeposit\"></app-deposits-input-form>\n\n    <!-- NEW DEPOSIT BUTTON -->\n    <div\n        class=\"app-control-component app-button app-button-new-account\"\n        *ngIf=\" !(state.selectedDeposit)\"\n        (click)= \"state.newDeposit()\"\n    >\n        New deposit\n    </div>\n</div>\n\n");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/section-exchange-rates/section-exchange-rates.component.html": 
        /*!*******************************************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/section-exchange-rates/section-exchange-rates.component.html ***!
          \*******************************************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<div *ngIf=\"state.allExchangeRatesHistoryLoaded\">\n\n<div\n    *ngFor=\"let crName of getCurrencies()\"\n    style=\"float: left; padding-right: 15px; padding-bottom: 15px;\"\n>\n    <div *ngIf=\"crName\">\n        <app-monthly-savings-report\n            [chartType]=\"'LineChart'\"\n            [width]=\"570\"\n            [height]=\"220\"\n            [hideDates]=\"true\"\n            [sourceDate]=\"getChartData(crName)\"\n            [sourceDataXValueMapper]=\"sourceXValueMapper\"\n            [sourceDataYValueMapper]=\"sourceYValueMapper\"\n            [chartColumnNames]=\"['Date', crName + ' Exchange Rate']\"\n        ></app-monthly-savings-report>\n    </div>\n\n</div>\n\n</div>");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/section-main/main.component.html": 
        /*!***************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/section-main/main.component.html ***!
          \***************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n<!-- Income or Expense Items and Categories lists -->\n<div class=\"generic-panel income-or-expense-items-editor-full\">\n    <div class=\"generic-panel-title\">Income or Expense Items Editor</div>\n    <app-income-or-expense-items-manager [allowEditCategories]=\"true\">\n    </app-income-or-expense-items-manager>\n</div>\n\n<div style=\"height: 5px;\"></div>\n\n<table class=\"income-or-expense-items-editor-full\"><tr>\n    <td>\n        <!-- Banks list -->\n        <div class=\"generic-panel income-or-expense-items-editor-full\" style=\"height: 280px;\">\n            <div class=\"generic-panel-title\">Banks Editor</div>\n            <app-banks-list></app-banks-list>\n        </div>\n    </td>\n    <td style=\"width: 5px\"></td>\n    <td>\n        <!-- Accounts list -->\n        <div class=\"generic-panel income-or-expense-items-editor-full\" style=\"height: 280px;\">\n            <div class=\"generic-panel-title\">Accounts Editor</div>\n            <app-accounts-manager></app-accounts-manager>\n        </div>\n    </td>\n    <td style=\"width: 5px\"></td>\n    <td>\n        <div class=\"generic-panel income-or-expense-items-editor-full\" style=\"height: 280px;\">\n            <div class=\"generic-panel-title\">Monitored Currencies</div>\n            <app-currencies-manager></app-currencies-manager>\n        </div>\n    </td>\n</tr></table>\n\n");
            /***/ 
        }),
        /***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/sliding-menu/sliding-menu.component.html": 
        /*!***********************************************************************************************************!*\
          !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/components/sliding-menu/sliding-menu.component.html ***!
          \***********************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("<div  id=\"sliding-menu\"\n      style=\"position: absolute; height: 100px; right: 60px; top: -70px; border-style: none;\"\n      class=\"sliding-menu-hidden\"\n\n      [@pullMenu]=currentState\n      (mouseover)=\"onMouseOver()\"\n      (mouseout)=\"onMouseOut()\"\n>\n    <div style=\"width: 100%; height: 60px;\" class=\"sliding-menu-icons-container\">\n        <table><tr><td>\n            <div class=\"sliding-menu-nav-icon nav-back-icon\" style=\"width:54px; height:54px; \" (click)=\"navigation.navigateBack()\"></div>\n\n            <div *ngFor=\"let navSection of navigation.getSections()\"\n                  class=\"sliding-menu-nav-icon {{navSection.iconCssClass}}\"\n                  style=\"width:54px; height:54px; \"\n                  (click)=\"navigation.navigateToSectionId(navSection.id)\"\n            ></div>\n        </td></tr></table>\n    </div>\n\n    <div style=\"width: 90px; height: 30px;\" class=\"sliding-menu-pulldown-button\"></div>\n</div>\n");
            /***/ 
        }),
        /***/ "./node_modules/tslib/tslib.es6.js": 
        /*!*****************************************!*\
          !*** ./node_modules/tslib/tslib.es6.js ***!
          \*****************************************/
        /*! exports provided: __extends, __assign, __rest, __decorate, __param, __metadata, __awaiter, __generator, __exportStar, __values, __read, __spread, __spreadArrays, __await, __asyncGenerator, __asyncDelegator, __asyncValues, __makeTemplateObject, __importStar, __importDefault */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__extends", function () { return __extends; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__assign", function () { return __assign; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__rest", function () { return __rest; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__decorate", function () { return __decorate; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__param", function () { return __param; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__metadata", function () { return __metadata; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__awaiter", function () { return __awaiter; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__generator", function () { return __generator; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__exportStar", function () { return __exportStar; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__values", function () { return __values; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__read", function () { return __read; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__spread", function () { return __spread; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__spreadArrays", function () { return __spreadArrays; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__await", function () { return __await; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__asyncGenerator", function () { return __asyncGenerator; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__asyncDelegator", function () { return __asyncDelegator; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__asyncValues", function () { return __asyncValues; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__makeTemplateObject", function () { return __makeTemplateObject; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__importStar", function () { return __importStar; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__importDefault", function () { return __importDefault; });
            /*! *****************************************************************************
            Copyright (c) Microsoft Corporation. All rights reserved.
            Licensed under the Apache License, Version 2.0 (the "License"); you may not use
            this file except in compliance with the License. You may obtain a copy of the
            License at http://www.apache.org/licenses/LICENSE-2.0
            
            THIS CODE IS PROVIDED ON AN *AS IS* BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
            KIND, EITHER EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION ANY IMPLIED
            WARRANTIES OR CONDITIONS OF TITLE, FITNESS FOR A PARTICULAR PURPOSE,
            MERCHANTABLITY OR NON-INFRINGEMENT.
            
            See the Apache Version 2.0 License for specific language governing permissions
            and limitations under the License.
            ***************************************************************************** */
            /* global Reflect, Promise */
            var extendStatics = function (d, b) {
                extendStatics = Object.setPrototypeOf ||
                    ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
                    function (d, b) { for (var p in b)
                        if (b.hasOwnProperty(p))
                            d[p] = b[p]; };
                return extendStatics(d, b);
            };
            function __extends(d, b) {
                extendStatics(d, b);
                function __() { this.constructor = d; }
                d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
            }
            var __assign = function () {
                __assign = Object.assign || function __assign(t) {
                    for (var s, i = 1, n = arguments.length; i < n; i++) {
                        s = arguments[i];
                        for (var p in s)
                            if (Object.prototype.hasOwnProperty.call(s, p))
                                t[p] = s[p];
                    }
                    return t;
                };
                return __assign.apply(this, arguments);
            };
            function __rest(s, e) {
                var t = {};
                for (var p in s)
                    if (Object.prototype.hasOwnProperty.call(s, p) && e.indexOf(p) < 0)
                        t[p] = s[p];
                if (s != null && typeof Object.getOwnPropertySymbols === "function")
                    for (var i = 0, p = Object.getOwnPropertySymbols(s); i < p.length; i++) {
                        if (e.indexOf(p[i]) < 0 && Object.prototype.propertyIsEnumerable.call(s, p[i]))
                            t[p[i]] = s[p[i]];
                    }
                return t;
            }
            function __decorate(decorators, target, key, desc) {
                var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
                if (typeof Reflect === "object" && typeof Reflect.decorate === "function")
                    r = Reflect.decorate(decorators, target, key, desc);
                else
                    for (var i = decorators.length - 1; i >= 0; i--)
                        if (d = decorators[i])
                            r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
                return c > 3 && r && Object.defineProperty(target, key, r), r;
            }
            function __param(paramIndex, decorator) {
                return function (target, key) { decorator(target, key, paramIndex); };
            }
            function __metadata(metadataKey, metadataValue) {
                if (typeof Reflect === "object" && typeof Reflect.metadata === "function")
                    return Reflect.metadata(metadataKey, metadataValue);
            }
            function __awaiter(thisArg, _arguments, P, generator) {
                return new (P || (P = Promise))(function (resolve, reject) {
                    function fulfilled(value) { try {
                        step(generator.next(value));
                    }
                    catch (e) {
                        reject(e);
                    } }
                    function rejected(value) { try {
                        step(generator["throw"](value));
                    }
                    catch (e) {
                        reject(e);
                    } }
                    function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
                    step((generator = generator.apply(thisArg, _arguments || [])).next());
                });
            }
            function __generator(thisArg, body) {
                var _ = { label: 0, sent: function () { if (t[0] & 1)
                        throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
                return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function () { return this; }), g;
                function verb(n) { return function (v) { return step([n, v]); }; }
                function step(op) {
                    if (f)
                        throw new TypeError("Generator is already executing.");
                    while (_)
                        try {
                            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done)
                                return t;
                            if (y = 0, t)
                                op = [op[0] & 2, t.value];
                            switch (op[0]) {
                                case 0:
                                case 1:
                                    t = op;
                                    break;
                                case 4:
                                    _.label++;
                                    return { value: op[1], done: false };
                                case 5:
                                    _.label++;
                                    y = op[1];
                                    op = [0];
                                    continue;
                                case 7:
                                    op = _.ops.pop();
                                    _.trys.pop();
                                    continue;
                                default:
                                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) {
                                        _ = 0;
                                        continue;
                                    }
                                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) {
                                        _.label = op[1];
                                        break;
                                    }
                                    if (op[0] === 6 && _.label < t[1]) {
                                        _.label = t[1];
                                        t = op;
                                        break;
                                    }
                                    if (t && _.label < t[2]) {
                                        _.label = t[2];
                                        _.ops.push(op);
                                        break;
                                    }
                                    if (t[2])
                                        _.ops.pop();
                                    _.trys.pop();
                                    continue;
                            }
                            op = body.call(thisArg, _);
                        }
                        catch (e) {
                            op = [6, e];
                            y = 0;
                        }
                        finally {
                            f = t = 0;
                        }
                    if (op[0] & 5)
                        throw op[1];
                    return { value: op[0] ? op[1] : void 0, done: true };
                }
            }
            function __exportStar(m, exports) {
                for (var p in m)
                    if (!exports.hasOwnProperty(p))
                        exports[p] = m[p];
            }
            function __values(o) {
                var m = typeof Symbol === "function" && o[Symbol.iterator], i = 0;
                if (m)
                    return m.call(o);
                return {
                    next: function () {
                        if (o && i >= o.length)
                            o = void 0;
                        return { value: o && o[i++], done: !o };
                    }
                };
            }
            function __read(o, n) {
                var m = typeof Symbol === "function" && o[Symbol.iterator];
                if (!m)
                    return o;
                var i = m.call(o), r, ar = [], e;
                try {
                    while ((n === void 0 || n-- > 0) && !(r = i.next()).done)
                        ar.push(r.value);
                }
                catch (error) {
                    e = { error: error };
                }
                finally {
                    try {
                        if (r && !r.done && (m = i["return"]))
                            m.call(i);
                    }
                    finally {
                        if (e)
                            throw e.error;
                    }
                }
                return ar;
            }
            function __spread() {
                for (var ar = [], i = 0; i < arguments.length; i++)
                    ar = ar.concat(__read(arguments[i]));
                return ar;
            }
            function __spreadArrays() {
                for (var s = 0, i = 0, il = arguments.length; i < il; i++)
                    s += arguments[i].length;
                for (var r = Array(s), k = 0, i = 0; i < il; i++)
                    for (var a = arguments[i], j = 0, jl = a.length; j < jl; j++, k++)
                        r[k] = a[j];
                return r;
            }
            ;
            function __await(v) {
                return this instanceof __await ? (this.v = v, this) : new __await(v);
            }
            function __asyncGenerator(thisArg, _arguments, generator) {
                if (!Symbol.asyncIterator)
                    throw new TypeError("Symbol.asyncIterator is not defined.");
                var g = generator.apply(thisArg, _arguments || []), i, q = [];
                return i = {}, verb("next"), verb("throw"), verb("return"), i[Symbol.asyncIterator] = function () { return this; }, i;
                function verb(n) { if (g[n])
                    i[n] = function (v) { return new Promise(function (a, b) { q.push([n, v, a, b]) > 1 || resume(n, v); }); }; }
                function resume(n, v) { try {
                    step(g[n](v));
                }
                catch (e) {
                    settle(q[0][3], e);
                } }
                function step(r) { r.value instanceof __await ? Promise.resolve(r.value.v).then(fulfill, reject) : settle(q[0][2], r); }
                function fulfill(value) { resume("next", value); }
                function reject(value) { resume("throw", value); }
                function settle(f, v) { if (f(v), q.shift(), q.length)
                    resume(q[0][0], q[0][1]); }
            }
            function __asyncDelegator(o) {
                var i, p;
                return i = {}, verb("next"), verb("throw", function (e) { throw e; }), verb("return"), i[Symbol.iterator] = function () { return this; }, i;
                function verb(n, f) { i[n] = o[n] ? function (v) { return (p = !p) ? { value: __await(o[n](v)), done: n === "return" } : f ? f(v) : v; } : f; }
            }
            function __asyncValues(o) {
                if (!Symbol.asyncIterator)
                    throw new TypeError("Symbol.asyncIterator is not defined.");
                var m = o[Symbol.asyncIterator], i;
                return m ? m.call(o) : (o = typeof __values === "function" ? __values(o) : o[Symbol.iterator](), i = {}, verb("next"), verb("throw"), verb("return"), i[Symbol.asyncIterator] = function () { return this; }, i);
                function verb(n) { i[n] = o[n] && function (v) { return new Promise(function (resolve, reject) { v = o[n](v), settle(resolve, reject, v.done, v.value); }); }; }
                function settle(resolve, reject, d, v) { Promise.resolve(v).then(function (v) { resolve({ value: v, done: d }); }, reject); }
            }
            function __makeTemplateObject(cooked, raw) {
                if (Object.defineProperty) {
                    Object.defineProperty(cooked, "raw", { value: raw });
                }
                else {
                    cooked.raw = raw;
                }
                return cooked;
            }
            ;
            function __importStar(mod) {
                if (mod && mod.__esModule)
                    return mod;
                var result = {};
                if (mod != null)
                    for (var k in mod)
                        if (Object.hasOwnProperty.call(mod, k))
                            result[k] = mod[k];
                result.default = mod;
                return result;
            }
            function __importDefault(mod) {
                return (mod && mod.__esModule) ? mod : { default: mod };
            }
            /***/ 
        }),
        /***/ "./src/app/app-routing.module.ts": 
        /*!***************************************!*\
          !*** ./src/app/app-routing.module.ts ***!
          \***************************************/
        /*! exports provided: AppRoutingModule */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppRoutingModule", function () { return AppRoutingModule; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm2015/router.js");
            var routes = [];
            var AppRoutingModule = /** @class */ (function () {
                function AppRoutingModule() {
                }
                return AppRoutingModule;
            }());
            AppRoutingModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
                    imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forRoot(routes)],
                    exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]]
                })
            ], AppRoutingModule);
            /***/ 
        }),
        /***/ "./src/app/app.component.css": 
        /*!***********************************!*\
          !*** ./src/app/app.component.css ***!
          \***********************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\r\n\r\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2FwcC5jb21wb25lbnQuY3NzIn0= */");
            /***/ 
        }),
        /***/ "./src/app/app.component.ts": 
        /*!**********************************!*\
          !*** ./src/app/app.component.ts ***!
          \**********************************/
        /*! exports provided: AppComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function () { return AppComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var _reusable_dom_ops__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./reusable/dom.ops */ "./src/app/reusable/dom.ops.ts");
            /* harmony import */ var _services_navigation_service_navigation_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./services/navigation-service/navigation.service */ "./src/app/services/navigation-service/navigation.service.ts");
            /* harmony import */ var src_environments_environment__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! src/environments/environment */ "./src/environments/environment.ts");
            var AppComponent = /** @class */ (function () {
                function AppComponent(navigation) {
                    this.navigation = navigation;
                    this.title = 'acct-gui';
                    _reusable_dom_ops__WEBPACK_IMPORTED_MODULE_2__["DomOps"].getTagByTypeAndName("link", "customSkin")
                        .setAttribute("href", 'assets/skins/' + src_environments_environment__WEBPACK_IMPORTED_MODULE_4__["environment"].skin + '/main.css');
                }
                return AppComponent;
            }());
            AppComponent.ctorParameters = function () { return [
                { type: _services_navigation_service_navigation_service__WEBPACK_IMPORTED_MODULE_3__["NavigationService"] }
            ]; };
            AppComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-root',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./app.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/app.component.html")).default,
                    encapsulation: _angular_core__WEBPACK_IMPORTED_MODULE_1__["ViewEncapsulation"].None,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./app.component.css */ "./src/app/app.component.css")).default]
                })
            ], AppComponent);
            /***/ 
        }),
        /***/ "./src/app/app.module.ts": 
        /*!*******************************!*\
          !*** ./src/app/app.module.ts ***!
          \*******************************/
        /*! exports provided: AppModule */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function () { return AppModule; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm2015/platform-browser.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm2015/http.js");
            /* harmony import */ var _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/platform-browser/animations */ "./node_modules/@angular/platform-browser/fesm2015/animations.js");
            /* harmony import */ var _app_routing_module__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./app-routing.module */ "./src/app/app-routing.module.ts");
            /* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
            /* harmony import */ var _components_accounts_list_accounts_list_component__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./components/accounts-list/accounts-list.component */ "./src/app/components/accounts-list/accounts-list.component.ts");
            /* harmony import */ var _components_section_main_main_component__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./components/section-main/main.component */ "./src/app/components/section-main/main.component.ts");
            /* harmony import */ var _components_section_dashboard_dashboard_component__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./components/section-dashboard/dashboard.component */ "./src/app/components/section-dashboard/dashboard.component.ts");
            /* harmony import */ var _components_section_accounts_accounts_component__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ./components/section-accounts/accounts.component */ "./src/app/components/section-accounts/accounts.component.ts");
            /* harmony import */ var _components_section_deposits_deposits_component__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! ./components/section-deposits/deposits.component */ "./src/app/components/section-deposits/deposits.component.ts");
            /* harmony import */ var _components_sliding_menu_sliding_menu_component__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! ./components/sliding-menu/sliding-menu.component */ "./src/app/components/sliding-menu/sliding-menu.component.ts");
            /* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm2015/forms.js");
            /* harmony import */ var _angular_material_datepicker__WEBPACK_IMPORTED_MODULE_14__ = __webpack_require__(/*! @angular/material/datepicker */ "./node_modules/@angular/material/esm2015/datepicker.js");
            /* harmony import */ var _angular_material_form_field__WEBPACK_IMPORTED_MODULE_15__ = __webpack_require__(/*! @angular/material/form-field */ "./node_modules/@angular/material/esm2015/form-field.js");
            /* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_16__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm2015/material.js");
            /* harmony import */ var _angular_material_dialog__WEBPACK_IMPORTED_MODULE_17__ = __webpack_require__(/*! @angular/material/dialog */ "./node_modules/@angular/material/esm2015/dialog.js");
            /* harmony import */ var _reusable_formats__WEBPACK_IMPORTED_MODULE_18__ = __webpack_require__(/*! ./reusable/formats */ "./src/app/reusable/formats.ts");
            /* harmony import */ var _angular_material_moment_adapter__WEBPACK_IMPORTED_MODULE_19__ = __webpack_require__(/*! @angular/material-moment-adapter */ "./node_modules/@angular/material-moment-adapter/esm2015/material-moment-adapter.js");
            /* harmony import */ var angular_google_charts__WEBPACK_IMPORTED_MODULE_20__ = __webpack_require__(/*! angular-google-charts */ "./node_modules/angular-google-charts/fesm2015/angular-google-charts.js");
            /* harmony import */ var _components_income_or_expense_category_select_income_or_expense_category_select_component__WEBPACK_IMPORTED_MODULE_21__ = __webpack_require__(/*! ./components/income-or-expense-category-select/income-or-expense-category-select.component */ "./src/app/components/income-or-expense-category-select/income-or-expense-category-select.component.ts");
            /* harmony import */ var _components_account_record_account_record_component__WEBPACK_IMPORTED_MODULE_22__ = __webpack_require__(/*! ./components/account-record/account-record.component */ "./src/app/components/account-record/account-record.component.ts");
            /* harmony import */ var _components_account_summary_account_summary_component__WEBPACK_IMPORTED_MODULE_23__ = __webpack_require__(/*! ./components/account-summary/account-summary.component */ "./src/app/components/account-summary/account-summary.component.ts");
            /* harmony import */ var _components_account_records_account_records_component__WEBPACK_IMPORTED_MODULE_24__ = __webpack_require__(/*! ./components/account-records/account-records.component */ "./src/app/components/account-records/account-records.component.ts");
            /* harmony import */ var _components_account_records_input_form_account_records_input_form_component__WEBPACK_IMPORTED_MODULE_25__ = __webpack_require__(/*! ./components/account-records-input-form/account-records-input-form.component */ "./src/app/components/account-records-input-form/account-records-input-form.component.ts");
            /* harmony import */ var _components_dialog_error_dialog_error_component__WEBPACK_IMPORTED_MODULE_26__ = __webpack_require__(/*! ./components/dialog-error/dialog-error.component */ "./src/app/components/dialog-error/dialog-error.component.ts");
            /* harmony import */ var _components_dialog_yes_no_dialog_yes_no_component__WEBPACK_IMPORTED_MODULE_27__ = __webpack_require__(/*! ./components/dialog-yes-no/dialog-yes-no.component */ "./src/app/components/dialog-yes-no/dialog-yes-no.component.ts");
            /* harmony import */ var _components_income_or_expense_items_manager_income_or_expense_items_manager_component__WEBPACK_IMPORTED_MODULE_28__ = __webpack_require__(/*! ./components/income-or-expense-items-manager/income-or-expense-items-manager.component */ "./src/app/components/income-or-expense-items-manager/income-or-expense-items-manager.component.ts");
            /* harmony import */ var _components_editable_names_list_editable_names_list_component__WEBPACK_IMPORTED_MODULE_29__ = __webpack_require__(/*! ./components/editable-names-list/editable-names-list.component */ "./src/app/components/editable-names-list/editable-names-list.component.ts");
            /* harmony import */ var _components_accounts_manager_accounts_manager_component__WEBPACK_IMPORTED_MODULE_30__ = __webpack_require__(/*! ./components/accounts-manager/accounts-manager.component */ "./src/app/components/accounts-manager/accounts-manager.component.ts");
            /* harmony import */ var _components_banks_list_banks_list_component__WEBPACK_IMPORTED_MODULE_31__ = __webpack_require__(/*! ./components/banks-list/banks-list.component */ "./src/app/components/banks-list/banks-list.component.ts");
            /* harmony import */ var _components_deposits_table_deposits_table_component__WEBPACK_IMPORTED_MODULE_32__ = __webpack_require__(/*! ./components/deposits-table/deposits-table.component */ "./src/app/components/deposits-table/deposits-table.component.ts");
            /* harmony import */ var _components_deposits_input_form_deposits_input_form_component__WEBPACK_IMPORTED_MODULE_33__ = __webpack_require__(/*! ./components/deposits-input-form/deposits-input-form.component */ "./src/app/components/deposits-input-form/deposits-input-form.component.ts");
            /* harmony import */ var _pipes_iso_date_pipe__WEBPACK_IMPORTED_MODULE_34__ = __webpack_require__(/*! ./pipes/iso-date.pipe */ "./src/app/pipes/iso-date.pipe.ts");
            /* harmony import */ var _components_progress_bar_progress_bar_component__WEBPACK_IMPORTED_MODULE_35__ = __webpack_require__(/*! ./components/progress-bar/progress-bar.component */ "./src/app/components/progress-bar/progress-bar.component.ts");
            /* harmony import */ var _components_monthly_balance_report_monthly_balance_report_component__WEBPACK_IMPORTED_MODULE_36__ = __webpack_require__(/*! ./components/monthly-balance-report/monthly-balance-report.component */ "./src/app/components/monthly-balance-report/monthly-balance-report.component.ts");
            /* harmony import */ var _components_monthly_savings_report_monthly_savings_report_component__WEBPACK_IMPORTED_MODULE_37__ = __webpack_require__(/*! ./components/monthly-savings-report/monthly-savings-report.component */ "./src/app/components/monthly-savings-report/monthly-savings-report.component.ts");
            /* harmony import */ var _components_currencies_manager_currencies_manager_component__WEBPACK_IMPORTED_MODULE_38__ = __webpack_require__(/*! ./components/currencies-manager/currencies-manager.component */ "./src/app/components/currencies-manager/currencies-manager.component.ts");
            /* harmony import */ var _components_section_exchange_rates_section_exchange_rates_component__WEBPACK_IMPORTED_MODULE_39__ = __webpack_require__(/*! ./components/section-exchange-rates/section-exchange-rates.component */ "./src/app/components/section-exchange-rates/section-exchange-rates.component.ts");
            /* harmony import */ var _components_money_transfer_form_money_transfer_form_component__WEBPACK_IMPORTED_MODULE_40__ = __webpack_require__(/*! ./components/money-transfer-form/money-transfer-form.component */ "./src/app/components/money-transfer-form/money-transfer-form.component.ts");
            // https://stackoverflow.com/questions/38892771/cant-bind-to-ngmodel-since-it-isnt-a-known-property-of-input
            // https://material.angular.io/components/datepicker/api
            // https://stackoverflow.com/questions/53359598/how-to-change-angular-material-datepicker-format
            // Google charts - https://www.tutorialspoint.com/angular_googlecharts/angular_googlecharts_quick_guide.htm
            // don't forget [npm i @angular/material-moment-adapter] and [npm i moment]
            var AppModule = /** @class */ (function () {
                function AppModule() {
                }
                return AppModule;
            }());
            AppModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["NgModule"])({
                    declarations: [
                        _app_component__WEBPACK_IMPORTED_MODULE_6__["AppComponent"],
                        _components_accounts_list_accounts_list_component__WEBPACK_IMPORTED_MODULE_7__["AccountsListComponent"],
                        _components_section_main_main_component__WEBPACK_IMPORTED_MODULE_8__["MainSectionComponent"],
                        _components_section_dashboard_dashboard_component__WEBPACK_IMPORTED_MODULE_9__["DashboardSectionComponent"],
                        _components_section_accounts_accounts_component__WEBPACK_IMPORTED_MODULE_10__["AccountsSectionComponent"],
                        _components_section_deposits_deposits_component__WEBPACK_IMPORTED_MODULE_11__["DepositsSectionComponent"],
                        _components_sliding_menu_sliding_menu_component__WEBPACK_IMPORTED_MODULE_12__["SlidingMenuComponent"],
                        _components_income_or_expense_category_select_income_or_expense_category_select_component__WEBPACK_IMPORTED_MODULE_21__["IncomeOrExpenseCategorySelectComponent"],
                        _components_account_record_account_record_component__WEBPACK_IMPORTED_MODULE_22__["AccountRecordComponent"],
                        _components_account_summary_account_summary_component__WEBPACK_IMPORTED_MODULE_23__["AccountSummaryComponent"],
                        _components_account_records_account_records_component__WEBPACK_IMPORTED_MODULE_24__["AccountRecordsComponent"],
                        _components_account_records_input_form_account_records_input_form_component__WEBPACK_IMPORTED_MODULE_25__["AccountRecordsInputFormComponent"],
                        _components_dialog_error_dialog_error_component__WEBPACK_IMPORTED_MODULE_26__["DialogErrorComponent"],
                        _components_dialog_yes_no_dialog_yes_no_component__WEBPACK_IMPORTED_MODULE_27__["DialogYesNoComponent"],
                        _components_income_or_expense_items_manager_income_or_expense_items_manager_component__WEBPACK_IMPORTED_MODULE_28__["IncomeOrExpenseItemsManagerComponent"],
                        _components_editable_names_list_editable_names_list_component__WEBPACK_IMPORTED_MODULE_29__["EditableNamesListComponent"],
                        _components_accounts_manager_accounts_manager_component__WEBPACK_IMPORTED_MODULE_30__["AccountsManagerComponent"],
                        _components_banks_list_banks_list_component__WEBPACK_IMPORTED_MODULE_31__["BanksListComponent"],
                        _components_deposits_table_deposits_table_component__WEBPACK_IMPORTED_MODULE_32__["DepositsTableComponent"],
                        _components_deposits_input_form_deposits_input_form_component__WEBPACK_IMPORTED_MODULE_33__["DepositsInputFormComponent"],
                        _pipes_iso_date_pipe__WEBPACK_IMPORTED_MODULE_34__["IsoDatePipe"],
                        _components_progress_bar_progress_bar_component__WEBPACK_IMPORTED_MODULE_35__["ProgressBarComponent"],
                        _components_monthly_balance_report_monthly_balance_report_component__WEBPACK_IMPORTED_MODULE_36__["MonthlyBalanceReportComponent"],
                        _components_monthly_savings_report_monthly_savings_report_component__WEBPACK_IMPORTED_MODULE_37__["MonthlySavingsReportComponent"],
                        _components_currencies_manager_currencies_manager_component__WEBPACK_IMPORTED_MODULE_38__["CurrenciesManagerComponent"],
                        _components_section_exchange_rates_section_exchange_rates_component__WEBPACK_IMPORTED_MODULE_39__["SectionExchangeRatesComponent"],
                        _components_money_transfer_form_money_transfer_form_component__WEBPACK_IMPORTED_MODULE_40__["MoneyTransferFormComponent"]
                    ],
                    imports: [
                        _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__["BrowserModule"],
                        _app_routing_module__WEBPACK_IMPORTED_MODULE_5__["AppRoutingModule"],
                        _angular_common_http__WEBPACK_IMPORTED_MODULE_3__["HttpClientModule"],
                        _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_4__["BrowserAnimationsModule"],
                        _angular_forms__WEBPACK_IMPORTED_MODULE_13__["FormsModule"],
                        _angular_material_datepicker__WEBPACK_IMPORTED_MODULE_14__["MatDatepickerModule"],
                        _angular_material_form_field__WEBPACK_IMPORTED_MODULE_15__["MatFormFieldModule"],
                        _angular_material__WEBPACK_IMPORTED_MODULE_16__["MatNativeDateModule"],
                        _angular_material__WEBPACK_IMPORTED_MODULE_16__["MatInputModule"],
                        _angular_material_dialog__WEBPACK_IMPORTED_MODULE_17__["MatDialogModule"],
                        angular_google_charts__WEBPACK_IMPORTED_MODULE_20__["GoogleChartsModule"]
                    ],
                    providers: [
                        { provide: _angular_material__WEBPACK_IMPORTED_MODULE_16__["DateAdapter"], useClass: _angular_material_moment_adapter__WEBPACK_IMPORTED_MODULE_19__["MomentDateAdapter"], deps: [_angular_material__WEBPACK_IMPORTED_MODULE_16__["MAT_DATE_LOCALE"]] },
                        { provide: _angular_material__WEBPACK_IMPORTED_MODULE_16__["MAT_DATE_FORMATS"], useValue: _reusable_formats__WEBPACK_IMPORTED_MODULE_18__["MY_FORMATS"] }
                    ],
                    bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_6__["AppComponent"]],
                    entryComponents: [_components_dialog_error_dialog_error_component__WEBPACK_IMPORTED_MODULE_26__["DialogErrorComponent"], _components_dialog_yes_no_dialog_yes_no_component__WEBPACK_IMPORTED_MODULE_27__["DialogYesNoComponent"], _components_income_or_expense_items_manager_income_or_expense_items_manager_component__WEBPACK_IMPORTED_MODULE_28__["IncomeOrExpenseItemsManagerComponent"], _components_accounts_manager_accounts_manager_component__WEBPACK_IMPORTED_MODULE_30__["AccountsManagerComponent"], _components_money_transfer_form_money_transfer_form_component__WEBPACK_IMPORTED_MODULE_40__["MoneyTransferFormComponent"]]
                })
            ], AppModule);
            /***/ 
        }),
        /***/ "./src/app/components/account-record/account-record.component.css": 
        /*!************************************************************************!*\
          !*** ./src/app/components/account-record/account-record.component.css ***!
          \************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvYWNjb3VudC1yZWNvcmQvYWNjb3VudC1yZWNvcmQuY29tcG9uZW50LmNzcyJ9 */");
            /***/ 
        }),
        /***/ "./src/app/components/account-record/account-record.component.ts": 
        /*!***********************************************************************!*\
          !*** ./src/app/components/account-record/account-record.component.ts ***!
          \***********************************************************************/
        /*! exports provided: AccountRecordComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AccountRecordComponent", function () { return AccountRecordComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/services/state-service/state.service */ "./src/app/services/state-service/state.service.ts");
            var AccountRecordComponent = /** @class */ (function () {
                function AccountRecordComponent(state) {
                    this.state = state;
                }
                AccountRecordComponent.prototype.ngOnInit = function () {
                    this.state.loadIncomeOrExpenseItemCategories();
                };
                AccountRecordComponent.prototype.getIncomeOrExpenseItemName = function (itemId) {
                    var ret = itemId ? this.state.getIncomeOrExpenseItem(itemId) : null;
                    if (ret == null || ret == undefined) {
                        return "ERROR: Income or expense item not found";
                    }
                    ;
                    return ret.name;
                };
                AccountRecordComponent.prototype.getIncomeOrExpenseItemCategoryName = function (itemId) {
                    if (itemId && this.state.incomeOrExpenseItems[itemId.valueOf()]) {
                        var catId = this.state.getIncomeOrExpenseItem(itemId).incomeOrExpenseItemCategoryId;
                        if (this.state.incomeOrExpenseItemCategories) {
                            for (var c = 0; c < this.state.incomeOrExpenseItemCategories.length; c++) {
                                if (this.state.incomeOrExpenseItemCategories[c].id == catId) {
                                    return this.state.incomeOrExpenseItemCategories[c].name;
                                }
                            }
                        }
                    }
                    return null;
                };
                return AccountRecordComponent;
            }());
            AccountRecordComponent.ctorParameters = function () { return [
                { type: src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__["StateService"] }
            ]; };
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], AccountRecordComponent.prototype, "rec", void 0);
            AccountRecordComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-account-record',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./account-record.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/account-record/account-record.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./account-record.component.css */ "./src/app/components/account-record/account-record.component.css")).default]
                })
            ], AccountRecordComponent);
            /***/ 
        }),
        /***/ "./src/app/components/account-records-input-form/account-records-input-form.component.css": 
        /*!************************************************************************************************!*\
          !*** ./src/app/components/account-records-input-form/account-records-input-form.component.css ***!
          \************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvYWNjb3VudC1yZWNvcmRzLWlucHV0LWZvcm0vYWNjb3VudC1yZWNvcmRzLWlucHV0LWZvcm0uY29tcG9uZW50LmNzcyJ9 */");
            /***/ 
        }),
        /***/ "./src/app/components/account-records-input-form/account-records-input-form.component.ts": 
        /*!***********************************************************************************************!*\
          !*** ./src/app/components/account-records-input-form/account-records-input-form.component.ts ***!
          \***********************************************************************************************/
        /*! exports provided: AccountRecordsInputFormComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AccountRecordsInputFormComponent", function () { return AccountRecordsInputFormComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/services/state-service/state.service */ "./src/app/services/state-service/state.service.ts");
            /* harmony import */ var _income_or_expense_items_manager_income_or_expense_items_manager_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../income-or-expense-items-manager/income-or-expense-items-manager.component */ "./src/app/components/income-or-expense-items-manager/income-or-expense-items-manager.component.ts");
            /* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm2015/material.js");
            var AccountRecordsInputFormComponent = /** @class */ (function () {
                function AccountRecordsInputFormComponent(state, dialog) {
                    this.state = state;
                    this.dialog = dialog;
                }
                AccountRecordsInputFormComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    // https://stackoverflow.com/questions/37587732/how-to-call-another-components-function-in-angular2
                    this.state.selectedRecordChangedObservable.subscribe(function (rec) {
                        _this.selectedAccountRecordToEditFormVariables(_this.state.selectedAccountRecord);
                    });
                };
                AccountRecordsInputFormComponent.prototype.selectedAccountRecordToEditFormVariables = function (rec) {
                    this.selectedIncomeOrExpenseItem = this.state.getIncomeOrExpenseItem(rec.incomeOrExpenseItemId);
                    this.selectedAccountRecordDateStr = new Date(this.state.selectedAccountRecord.date.toString()).toISOString().split("T")[0];
                };
                AccountRecordsInputFormComponent.prototype.editFormVariablesToSelectedAccountRecord = function () {
                    console.log(this.selectedIncomeOrExpenseItem);
                    this.state.selectedAccountRecord.incomeOrExpenseItemId = this.selectedIncomeOrExpenseItem.id;
                    this.state.selectedAccountRecord.date = new Date(this.selectedAccountRecordDateStr);
                    this.selectedAccountRecordDateStr = null;
                };
                AccountRecordsInputFormComponent.prototype.onValueKeyUp = function (event) {
                    if (event.key == "Enter") {
                        this.editFormVariablesToSelectedAccountRecord();
                        this.state.saveSelectedAccountRecord();
                    }
                };
                AccountRecordsInputFormComponent.prototype.onItemChange = function (event) {
                    this.state.selectedAccountRecord.value = this.selectedIncomeOrExpenseItem.lastUsedValue;
                    if (!(this.state.selectedAccountRecord.value)) {
                        this.state.selectedAccountRecord.value = 0;
                    }
                };
                AccountRecordsInputFormComponent.prototype.showIncomeOrExpenseItemsManager = function () {
                    this.itemsManagerDialogRef = this.dialog.open(_income_or_expense_items_manager_income_or_expense_items_manager_component__WEBPACK_IMPORTED_MODULE_3__["IncomeOrExpenseItemsManagerComponent"], {
                        width: '400px',
                        height: '300px'
                    });
                };
                AccountRecordsInputFormComponent.prototype.getCatItemName = function (item) {
                    return this.getImpactOrExpenseItemCategoryName(item.incomeOrExpenseItemCategoryId) + " - " + item.name;
                };
                AccountRecordsInputFormComponent.prototype.getImpactOrExpenseItemCategoryName = function (catId) {
                    var e_1, _a;
                    try {
                        for (var _b = __values(this.state.incomeOrExpenseItemCategories), _c = _b.next(); !_c.done; _c = _b.next()) {
                            var cat = _c.value;
                            if (cat.id == catId) {
                                return cat.name.valueOf();
                            }
                        }
                    }
                    catch (e_1_1) { e_1 = { error: e_1_1 }; }
                    finally {
                        try {
                            if (_c && !_c.done && (_a = _b.return)) _a.call(_b);
                        }
                        finally { if (e_1) throw e_1.error; }
                    }
                    return "???";
                };
                return AccountRecordsInputFormComponent;
            }());
            AccountRecordsInputFormComponent.ctorParameters = function () { return [
                { type: src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__["StateService"] },
                { type: _angular_material__WEBPACK_IMPORTED_MODULE_4__["MatDialog"] }
            ]; };
            AccountRecordsInputFormComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-account-records-input-form',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./account-records-input-form.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/account-records-input-form/account-records-input-form.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./account-records-input-form.component.css */ "./src/app/components/account-records-input-form/account-records-input-form.component.css")).default]
                })
            ], AccountRecordsInputFormComponent);
            /***/ 
        }),
        /***/ "./src/app/components/account-records/account-records.component.css": 
        /*!**************************************************************************!*\
          !*** ./src/app/components/account-records/account-records.component.css ***!
          \**************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvYWNjb3VudC1yZWNvcmRzL2FjY291bnQtcmVjb3Jkcy5jb21wb25lbnQuY3NzIn0= */");
            /***/ 
        }),
        /***/ "./src/app/components/account-records/account-records.component.ts": 
        /*!*************************************************************************!*\
          !*** ./src/app/components/account-records/account-records.component.ts ***!
          \*************************************************************************/
        /*! exports provided: AccountRecordsComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AccountRecordsComponent", function () { return AccountRecordsComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/services/state-service/state.service */ "./src/app/services/state-service/state.service.ts");
            var AccountRecordsComponent = /** @class */ (function () {
                function AccountRecordsComponent(state) {
                    this.state = state;
                }
                AccountRecordsComponent.prototype.ngOnInit = function () {
                };
                return AccountRecordsComponent;
            }());
            AccountRecordsComponent.ctorParameters = function () { return [
                { type: src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__["StateService"] }
            ]; };
            AccountRecordsComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-account-records',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./account-records.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/account-records/account-records.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./account-records.component.css */ "./src/app/components/account-records/account-records.component.css")).default]
                })
            ], AccountRecordsComponent);
            /***/ 
        }),
        /***/ "./src/app/components/account-summary/account-summary.component.css": 
        /*!**************************************************************************!*\
          !*** ./src/app/components/account-summary/account-summary.component.css ***!
          \**************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvYWNjb3VudC1zdW1tYXJ5L2FjY291bnQtc3VtbWFyeS5jb21wb25lbnQuY3NzIn0= */");
            /***/ 
        }),
        /***/ "./src/app/components/account-summary/account-summary.component.ts": 
        /*!*************************************************************************!*\
          !*** ./src/app/components/account-summary/account-summary.component.ts ***!
          \*************************************************************************/
        /*! exports provided: AccountSummaryComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AccountSummaryComponent", function () { return AccountSummaryComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            var AccountSummaryComponent = /** @class */ (function () {
                function AccountSummaryComponent() {
                }
                AccountSummaryComponent.prototype.ngOnInit = function () {
                };
                return AccountSummaryComponent;
            }());
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], AccountSummaryComponent.prototype, "summary", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], AccountSummaryComponent.prototype, "account", void 0);
            AccountSummaryComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-account-summary',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./account-summary.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/account-summary/account-summary.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./account-summary.component.css */ "./src/app/components/account-summary/account-summary.component.css")).default]
                })
            ], AccountSummaryComponent);
            /***/ 
        }),
        /***/ "./src/app/components/accounts-list/accounts-list.component.css": 
        /*!**********************************************************************!*\
          !*** ./src/app/components/accounts-list/accounts-list.component.css ***!
          \**********************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvYWNjb3VudHMtbGlzdC9hY2NvdW50cy1saXN0LmNvbXBvbmVudC5jc3MifQ== */");
            /***/ 
        }),
        /***/ "./src/app/components/accounts-list/accounts-list.component.ts": 
        /*!*********************************************************************!*\
          !*** ./src/app/components/accounts-list/accounts-list.component.ts ***!
          \*********************************************************************/
        /*! exports provided: AccountsListComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AccountsListComponent", function () { return AccountsListComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/services/state-service/state.service */ "./src/app/services/state-service/state.service.ts");
            var AccountsListComponent = /** @class */ (function () {
                function AccountsListComponent(state) {
                    this.state = state;
                }
                AccountsListComponent.prototype.ngOnInit = function () {
                };
                AccountsListComponent.prototype.selectAccountId = function (accountId) {
                    this.state.selectAccountId(accountId);
                };
                return AccountsListComponent;
            }());
            AccountsListComponent.ctorParameters = function () { return [
                { type: src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__["StateService"] }
            ]; };
            AccountsListComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-accounts-list',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./accounts-list.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/accounts-list/accounts-list.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./accounts-list.component.css */ "./src/app/components/accounts-list/accounts-list.component.css")).default]
                })
            ], AccountsListComponent);
            /***/ 
        }),
        /***/ "./src/app/components/accounts-manager/accounts-manager.component.css": 
        /*!****************************************************************************!*\
          !*** ./src/app/components/accounts-manager/accounts-manager.component.css ***!
          \****************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvYWNjb3VudHMtbWFuYWdlci9hY2NvdW50cy1tYW5hZ2VyLmNvbXBvbmVudC5jc3MifQ== */");
            /***/ 
        }),
        /***/ "./src/app/components/accounts-manager/accounts-manager.component.ts": 
        /*!***************************************************************************!*\
          !*** ./src/app/components/accounts-manager/accounts-manager.component.ts ***!
          \***************************************************************************/
        /*! exports provided: AccountsManagerComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AccountsManagerComponent", function () { return AccountsManagerComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var _model_account__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../model/account */ "./src/app/model/account.ts");
            /* harmony import */ var src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! src/app/services/state-service/state.service */ "./src/app/services/state-service/state.service.ts");
            var AccountsManagerComponent = /** @class */ (function () {
                function AccountsManagerComponent(state) {
                    this.state = state;
                    this.getItemName = (function (item) { return item.name; });
                    this.newItem = (function () {
                        var acc = new _model_account__WEBPACK_IMPORTED_MODULE_2__["Account"](0, "New account");
                        return acc;
                    });
                }
                AccountsManagerComponent.prototype.ngOnInit = function () {
                    this.state.loadAccounts();
                };
                AccountsManagerComponent.prototype.selectItem = function (account) {
                    this.selectedAccount = account;
                };
                AccountsManagerComponent.prototype.addItem = function (account) {
                    this.selectedAccount = account;
                };
                AccountsManagerComponent.prototype.deleteItem = function (account) {
                };
                AccountsManagerComponent.prototype.beginEditItem = function (account) {
                    this.selectedAccount = account;
                };
                AccountsManagerComponent.prototype.endEditItem = function (name) {
                    this.selectedAccount.name = name;
                    this.state.saveAccount(this.selectedAccount);
                    this.selectedAccount = null;
                };
                return AccountsManagerComponent;
            }());
            AccountsManagerComponent.ctorParameters = function () { return [
                { type: src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_3__["StateService"] }
            ]; };
            AccountsManagerComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-accounts-manager',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./accounts-manager.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/accounts-manager/accounts-manager.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./accounts-manager.component.css */ "./src/app/components/accounts-manager/accounts-manager.component.css")).default]
                })
            ], AccountsManagerComponent);
            /***/ 
        }),
        /***/ "./src/app/components/banks-list/banks-list.component.css": 
        /*!****************************************************************!*\
          !*** ./src/app/components/banks-list/banks-list.component.css ***!
          \****************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvYmFua3MtbGlzdC9iYW5rcy1saXN0LmNvbXBvbmVudC5jc3MifQ== */");
            /***/ 
        }),
        /***/ "./src/app/components/banks-list/banks-list.component.ts": 
        /*!***************************************************************!*\
          !*** ./src/app/components/banks-list/banks-list.component.ts ***!
          \***************************************************************/
        /*! exports provided: BanksListComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "BanksListComponent", function () { return BanksListComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_model_bank__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/model/bank */ "./src/app/model/bank.ts");
            /* harmony import */ var src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! src/app/services/state-service/state.service */ "./src/app/services/state-service/state.service.ts");
            var BanksListComponent = /** @class */ (function () {
                function BanksListComponent(state) {
                    this.state = state;
                    this.getBankName = (function (bank) { return bank.name; });
                    this.newBank = (function () { return new src_app_model_bank__WEBPACK_IMPORTED_MODULE_2__["Bank"](0, "New bank"); });
                }
                BanksListComponent.prototype.ngOnInit = function () {
                    this.state.loadBanks();
                };
                BanksListComponent.prototype.selectBank = function (bank) {
                    this.selectedBank = bank;
                };
                BanksListComponent.prototype.addBank = function (bank) {
                    this.selectedBank = bank;
                };
                BanksListComponent.prototype.deleteBank = function (bank) {
                };
                BanksListComponent.prototype.beginEditBank = function (bank) {
                    this.selectedBank = bank;
                };
                BanksListComponent.prototype.endEditBank = function (bankName) {
                    this.selectedBank.name = bankName;
                    this.state.saveBank(this.selectedBank);
                };
                return BanksListComponent;
            }());
            BanksListComponent.ctorParameters = function () { return [
                { type: src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_3__["StateService"] }
            ]; };
            BanksListComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-banks-list',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./banks-list.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/banks-list/banks-list.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./banks-list.component.css */ "./src/app/components/banks-list/banks-list.component.css")).default]
                })
            ], BanksListComponent);
            /***/ 
        }),
        /***/ "./src/app/components/currencies-manager/currencies-manager.component.css": 
        /*!********************************************************************************!*\
          !*** ./src/app/components/currencies-manager/currencies-manager.component.css ***!
          \********************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvY3VycmVuY2llcy1tYW5hZ2VyL2N1cnJlbmNpZXMtbWFuYWdlci5jb21wb25lbnQuY3NzIn0= */");
            /***/ 
        }),
        /***/ "./src/app/components/currencies-manager/currencies-manager.component.ts": 
        /*!*******************************************************************************!*\
          !*** ./src/app/components/currencies-manager/currencies-manager.component.ts ***!
          \*******************************************************************************/
        /*! exports provided: CurrenciesManagerComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CurrenciesManagerComponent", function () { return CurrenciesManagerComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/services/state-service/state.service */ "./src/app/services/state-service/state.service.ts");
            var CurrenciesManagerComponent = /** @class */ (function () {
                function CurrenciesManagerComponent(state) {
                    this.state = state;
                    this.loading = false;
                }
                CurrenciesManagerComponent.prototype.ngOnInit = function () {
                    this.state.loadMonitoredCurrency();
                };
                CurrenciesManagerComponent.prototype.selectSupportedCurrency = function (item) {
                    this.selectedSupportedCurrency = item;
                };
                CurrenciesManagerComponent.prototype.selectMonitoredCurrency = function (item) {
                    this.selectedMonitoredCurrency = item;
                };
                CurrenciesManagerComponent.prototype.addButtonClicked = function () {
                    this.state.monitorCurrency(this.selectedSupportedCurrency);
                    this.selectedSupportedCurrency = null;
                };
                CurrenciesManagerComponent.prototype.deleteButtonClicked = function () {
                    this.state.unMonitorCurrency(this.selectedMonitoredCurrency);
                    this.selectedMonitoredCurrency = null;
                };
                CurrenciesManagerComponent.prototype.collectButtonClicked = function () {
                    var _this = this;
                    if (!(this.loading)) {
                        this.loading = true;
                        this.state.updateCurrenciesFromSource().subscribe(function (result) {
                            _this.loading = false;
                        });
                    }
                };
                return CurrenciesManagerComponent;
            }());
            CurrenciesManagerComponent.ctorParameters = function () { return [
                { type: src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__["StateService"] }
            ]; };
            CurrenciesManagerComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-currencies-manager',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./currencies-manager.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/currencies-manager/currencies-manager.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./currencies-manager.component.css */ "./src/app/components/currencies-manager/currencies-manager.component.css")).default]
                })
            ], CurrenciesManagerComponent);
            /***/ 
        }),
        /***/ "./src/app/components/deposits-input-form/deposits-input-form.component.css": 
        /*!**********************************************************************************!*\
          !*** ./src/app/components/deposits-input-form/deposits-input-form.component.css ***!
          \**********************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvZGVwb3NpdHMtaW5wdXQtZm9ybS9kZXBvc2l0cy1pbnB1dC1mb3JtLmNvbXBvbmVudC5jc3MifQ== */");
            /***/ 
        }),
        /***/ "./src/app/components/deposits-input-form/deposits-input-form.component.ts": 
        /*!*********************************************************************************!*\
          !*** ./src/app/components/deposits-input-form/deposits-input-form.component.ts ***!
          \*********************************************************************************/
        /*! exports provided: DepositsInputFormComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DepositsInputFormComponent", function () { return DepositsInputFormComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/services/state-service/state.service */ "./src/app/services/state-service/state.service.ts");
            /* harmony import */ var src_app_model_account__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! src/app/model/account */ "./src/app/model/account.ts");
            /* harmony import */ var src_app_model_bank__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! src/app/model/bank */ "./src/app/model/bank.ts");
            var DepositsInputFormComponent = /** @class */ (function () {
                function DepositsInputFormComponent(state) {
                    this.state = state;
                    this.sourceAccount = new src_app_model_account__WEBPACK_IMPORTED_MODULE_3__["Account"](0, "Not selected");
                    this.bank = new src_app_model_bank__WEBPACK_IMPORTED_MODULE_4__["Bank"](0, "Not selected");
                }
                DepositsInputFormComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    this.state.loadAccounts();
                    this.state.loadBanks();
                    this.state.selectedDepositChangedObservable.subscribe(function (deposit) {
                        _this.selectedDepositToLocalFields();
                    });
                };
                DepositsInputFormComponent.prototype.onTextFieldUp = function (event) {
                    if (event.key == "Enter") {
                        this.localFieldsToSelectedDeposit();
                        this.state.saveSelectedDeposit();
                    }
                };
                DepositsInputFormComponent.prototype.onAccountNumberFieldUp = function (event) {
                    if (event.key == "Enter") {
                        this.state.selectedDeposit.accountNumber = this.accountNumber;
                        this.state.saveSelectedDeposit(true);
                    }
                };
                DepositsInputFormComponent.prototype.selectedDepositToLocalFields = function () {
                    this.sourceAccount = this.state.getAccount(this.state.selectedDeposit.sourceAccountId);
                    this.bank = this.state.getBank(this.state.selectedDeposit.bankId);
                    this.accountNumber = this.state.selectedDeposit.accountNumber;
                    this.startDateStr = new Date(this.state.selectedDeposit.startDate).toISOString().split("T")[0];
                    this.endDateStr = new Date(this.state.selectedDeposit.endDate).toISOString().split("T")[0];
                    this.value = this.state.selectedDeposit.value;
                    this.interestPercent = this.state.selectedDeposit.interestPercent;
                };
                DepositsInputFormComponent.prototype.localFieldsToSelectedDeposit = function () {
                    this.state.selectedDeposit.sourceAccountId = this.sourceAccount.id;
                    this.state.selectedDeposit.accountNumber = this.accountNumber;
                    this.state.selectedDeposit.bankId = this.bank.id;
                    this.state.selectedDeposit.startDate = new Date(this.startDateStr);
                    this.state.selectedDeposit.endDate = new Date(this.endDateStr);
                    this.state.selectedDeposit.value = this.value;
                    this.state.selectedDeposit.interestPercent = this.interestPercent;
                };
                return DepositsInputFormComponent;
            }());
            DepositsInputFormComponent.ctorParameters = function () { return [
                { type: src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__["StateService"] }
            ]; };
            DepositsInputFormComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-deposits-input-form',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./deposits-input-form.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/deposits-input-form/deposits-input-form.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./deposits-input-form.component.css */ "./src/app/components/deposits-input-form/deposits-input-form.component.css")).default]
                })
            ], DepositsInputFormComponent);
            /***/ 
        }),
        /***/ "./src/app/components/deposits-table/deposits-table.component.css": 
        /*!************************************************************************!*\
          !*** ./src/app/components/deposits-table/deposits-table.component.css ***!
          \************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvZGVwb3NpdHMtdGFibGUvZGVwb3NpdHMtdGFibGUuY29tcG9uZW50LmNzcyJ9 */");
            /***/ 
        }),
        /***/ "./src/app/components/deposits-table/deposits-table.component.ts": 
        /*!***********************************************************************!*\
          !*** ./src/app/components/deposits-table/deposits-table.component.ts ***!
          \***********************************************************************/
        /*! exports provided: DepositsTableComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DepositsTableComponent", function () { return DepositsTableComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/services/state-service/state.service */ "./src/app/services/state-service/state.service.ts");
            var DepositsTableComponent = /** @class */ (function () {
                function DepositsTableComponent(state) {
                    this.state = state;
                }
                DepositsTableComponent.prototype.ngOnInit = function () {
                    this.state.loadBanks();
                    this.state.loadAccounts();
                };
                DepositsTableComponent.prototype.getSourceAccountName = function (deposit) {
                    var acc = this.state.getAccount(deposit.sourceAccountId);
                    return acc ? acc.name : "No source account";
                };
                DepositsTableComponent.prototype.getBankName = function (deposit) {
                    var bnk = this.state.getBank(deposit.bankId);
                    return bnk ? bnk.name : "No bank";
                };
                DepositsTableComponent.prototype.computeProgress = function (rec) {
                    var startToEnd = (new Date(rec.endDate)).getTime() - (new Date(rec.startDate)).getTime();
                    var startToToday = (new Date()).getTime() - (new Date(rec.startDate)).getTime();
                    return Math.round(startToToday / startToEnd * 100);
                };
                return DepositsTableComponent;
            }());
            DepositsTableComponent.ctorParameters = function () { return [
                { type: src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__["StateService"] }
            ]; };
            DepositsTableComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-deposits-table',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./deposits-table.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/deposits-table/deposits-table.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./deposits-table.component.css */ "./src/app/components/deposits-table/deposits-table.component.css")).default]
                })
            ], DepositsTableComponent);
            /***/ 
        }),
        /***/ "./src/app/components/dialog-error/dialog-error.component.css": 
        /*!********************************************************************!*\
          !*** ./src/app/components/dialog-error/dialog-error.component.css ***!
          \********************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvZGlhbG9nLWVycm9yL2RpYWxvZy1lcnJvci5jb21wb25lbnQuY3NzIn0= */");
            /***/ 
        }),
        /***/ "./src/app/components/dialog-error/dialog-error.component.ts": 
        /*!*******************************************************************!*\
          !*** ./src/app/components/dialog-error/dialog-error.component.ts ***!
          \*******************************************************************/
        /*! exports provided: DialogErrorComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DialogErrorComponent", function () { return DialogErrorComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_services_dialog_service_dialog_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/services/dialog-service/dialog.service */ "./src/app/services/dialog-service/dialog.service.ts");
            var DialogErrorComponent = /** @class */ (function () {
                function DialogErrorComponent(dialogService) {
                    this.dialogService = dialogService;
                }
                DialogErrorComponent.prototype.ngOnInit = function () {
                };
                return DialogErrorComponent;
            }());
            DialogErrorComponent.ctorParameters = function () { return [
                { type: src_app_services_dialog_service_dialog_service__WEBPACK_IMPORTED_MODULE_2__["DialogService"] }
            ]; };
            DialogErrorComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-dialog-error',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./dialog-error.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/dialog-error/dialog-error.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./dialog-error.component.css */ "./src/app/components/dialog-error/dialog-error.component.css")).default]
                })
            ], DialogErrorComponent);
            /***/ 
        }),
        /***/ "./src/app/components/dialog-yes-no/dialog-yes-no.component.css": 
        /*!**********************************************************************!*\
          !*** ./src/app/components/dialog-yes-no/dialog-yes-no.component.css ***!
          \**********************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvZGlhbG9nLXllcy1uby9kaWFsb2cteWVzLW5vLmNvbXBvbmVudC5jc3MifQ== */");
            /***/ 
        }),
        /***/ "./src/app/components/dialog-yes-no/dialog-yes-no.component.ts": 
        /*!*********************************************************************!*\
          !*** ./src/app/components/dialog-yes-no/dialog-yes-no.component.ts ***!
          \*********************************************************************/
        /*! exports provided: DialogYesNoComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DialogYesNoComponent", function () { return DialogYesNoComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_services_dialog_service_dialog_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/services/dialog-service/dialog.service */ "./src/app/services/dialog-service/dialog.service.ts");
            var DialogYesNoComponent = /** @class */ (function () {
                function DialogYesNoComponent(dialogService) {
                    this.dialogService = dialogService;
                }
                DialogYesNoComponent.prototype.ngOnInit = function () {
                };
                return DialogYesNoComponent;
            }());
            DialogYesNoComponent.ctorParameters = function () { return [
                { type: src_app_services_dialog_service_dialog_service__WEBPACK_IMPORTED_MODULE_2__["DialogService"] }
            ]; };
            DialogYesNoComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-dialog-yes-no',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./dialog-yes-no.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/dialog-yes-no/dialog-yes-no.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./dialog-yes-no.component.css */ "./src/app/components/dialog-yes-no/dialog-yes-no.component.css")).default]
                })
            ], DialogYesNoComponent);
            /***/ 
        }),
        /***/ "./src/app/components/editable-names-list/editable-names-list.component.css": 
        /*!**********************************************************************************!*\
          !*** ./src/app/components/editable-names-list/editable-names-list.component.css ***!
          \**********************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvZWRpdGFibGUtbmFtZXMtbGlzdC9lZGl0YWJsZS1uYW1lcy1saXN0LmNvbXBvbmVudC5jc3MifQ== */");
            /***/ 
        }),
        /***/ "./src/app/components/editable-names-list/editable-names-list.component.ts": 
        /*!*********************************************************************************!*\
          !*** ./src/app/components/editable-names-list/editable-names-list.component.ts ***!
          \*********************************************************************************/
        /*! exports provided: EditableNamesListComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "EditableNamesListComponent", function () { return EditableNamesListComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/services/state-service/state.service */ "./src/app/services/state-service/state.service.ts");
            var EditableNamesListComponent = /** @class */ (function () {
                function EditableNamesListComponent(state) {
                    this.onSelect = new _angular_core__WEBPACK_IMPORTED_MODULE_1__["EventEmitter"]();
                    this.onAdd = new _angular_core__WEBPACK_IMPORTED_MODULE_1__["EventEmitter"]();
                    this.onDelete = new _angular_core__WEBPACK_IMPORTED_MODULE_1__["EventEmitter"]();
                    this.onEditBegin = new _angular_core__WEBPACK_IMPORTED_MODULE_1__["EventEmitter"]();
                    this.onEditEnd = new _angular_core__WEBPACK_IMPORTED_MODULE_1__["EventEmitter"]();
                }
                EditableNamesListComponent.prototype.ngOnInit = function () {
                };
                EditableNamesListComponent.prototype.ngOnChanges = function (changes) {
                    this.selectedItem = null;
                    this.selectedItemDisplatValue = null;
                };
                EditableNamesListComponent.prototype.selectItem = function (item) {
                    this.selectedItem = item;
                    this.onSelect.emit(item);
                };
                EditableNamesListComponent.prototype.addButtonClicked = function () {
                    this.selectedItem = this.newItemCallback();
                    this.listItems.push(this.selectedItem);
                    this.selectedItemDisplatValue = this.displayValueCallback(this.selectedItem);
                    this.onAdd.emit(this.selectedItem);
                };
                EditableNamesListComponent.prototype.editButtonClicked = function () {
                    this.selectedItemDisplatValue = this.displayValueCallback(this.selectedItem);
                    this.onEditBegin.emit(this.selectedItem);
                };
                EditableNamesListComponent.prototype.deleteButtonClicked = function () {
                    this.onDelete.emit(this.selectedItem);
                };
                EditableNamesListComponent.prototype.onEditInputKeyUp = function (event) {
                    if (event.key == "Enter") {
                        this.onEditEnd.emit(this.selectedItemDisplatValue);
                        this.selectedItemDisplatValue = null;
                    }
                };
                return EditableNamesListComponent;
            }());
            EditableNamesListComponent.ctorParameters = function () { return [
                { type: src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__["StateService"] }
            ]; };
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], EditableNamesListComponent.prototype, "listItems", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], EditableNamesListComponent.prototype, "displayValueCallback", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], EditableNamesListComponent.prototype, "newItemCallback", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Output"])()
            ], EditableNamesListComponent.prototype, "onSelect", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Output"])()
            ], EditableNamesListComponent.prototype, "onAdd", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Output"])()
            ], EditableNamesListComponent.prototype, "onDelete", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Output"])()
            ], EditableNamesListComponent.prototype, "onEditBegin", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Output"])()
            ], EditableNamesListComponent.prototype, "onEditEnd", void 0);
            EditableNamesListComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-editable-names-list',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./editable-names-list.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/editable-names-list/editable-names-list.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./editable-names-list.component.css */ "./src/app/components/editable-names-list/editable-names-list.component.css")).default]
                })
            ], EditableNamesListComponent);
            /***/ 
        }),
        /***/ "./src/app/components/income-or-expense-category-select/income-or-expense-category-select.component.css": 
        /*!**************************************************************************************************************!*\
          !*** ./src/app/components/income-or-expense-category-select/income-or-expense-category-select.component.css ***!
          \**************************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvaW5jb21lLW9yLWV4cGVuc2UtY2F0ZWdvcnktc2VsZWN0L2luY29tZS1vci1leHBlbnNlLWNhdGVnb3J5LXNlbGVjdC5jb21wb25lbnQuY3NzIn0= */");
            /***/ 
        }),
        /***/ "./src/app/components/income-or-expense-category-select/income-or-expense-category-select.component.ts": 
        /*!*************************************************************************************************************!*\
          !*** ./src/app/components/income-or-expense-category-select/income-or-expense-category-select.component.ts ***!
          \*************************************************************************************************************/
        /*! exports provided: IncomeOrExpenseCategorySelectComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "IncomeOrExpenseCategorySelectComponent", function () { return IncomeOrExpenseCategorySelectComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/services/state-service/state.service */ "./src/app/services/state-service/state.service.ts");
            var IncomeOrExpenseCategorySelectComponent = /** @class */ (function () {
                /* Two way binding of selectedIncomeOrExpenseItemObj END */
                function IncomeOrExpenseCategorySelectComponent(state) {
                    this.state = state;
                    this.selectedIncomeOrExpenseItemChange = new _angular_core__WEBPACK_IMPORTED_MODULE_1__["EventEmitter"]();
                }
                Object.defineProperty(IncomeOrExpenseCategorySelectComponent.prototype, "selectedIncomeOrExpenseItem", {
                    get: function () {
                        return this.selectedIncomeOrExpenseItemObj;
                    },
                    set: function (val) {
                        this.selectedIncomeOrExpenseItemObj = val;
                    },
                    enumerable: true,
                    configurable: true
                });
                ;
                IncomeOrExpenseCategorySelectComponent.prototype.onChange = function () {
                    this.selectedIncomeOrExpenseItemChange.emit(this.selectedIncomeOrExpenseItemObj);
                };
                IncomeOrExpenseCategorySelectComponent.prototype.ngOnInit = function () {
                    this.state.loadIncomeOrExpenseItems();
                };
                IncomeOrExpenseCategorySelectComponent.prototype.getCatItemName = function (item) {
                    return this.getImpactOrExpenseItemCategoryName(item.incomeOrExpenseItemCategoryId) + " - " + item.name;
                };
                IncomeOrExpenseCategorySelectComponent.prototype.getImpactOrExpenseItemCategoryName = function (catId) {
                    var e_2, _a;
                    try {
                        for (var _b = __values(this.state.incomeOrExpenseItemCategories), _c = _b.next(); !_c.done; _c = _b.next()) {
                            var cat = _c.value;
                            if (cat.id == catId) {
                                return cat.name.valueOf();
                            }
                        }
                    }
                    catch (e_2_1) { e_2 = { error: e_2_1 }; }
                    finally {
                        try {
                            if (_c && !_c.done && (_a = _b.return)) _a.call(_b);
                        }
                        finally { if (e_2) throw e_2.error; }
                    }
                    return "???";
                };
                return IncomeOrExpenseCategorySelectComponent;
            }());
            IncomeOrExpenseCategorySelectComponent.ctorParameters = function () { return [
                { type: src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__["StateService"] }
            ]; };
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], IncomeOrExpenseCategorySelectComponent.prototype, "cssClassName", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], IncomeOrExpenseCategorySelectComponent.prototype, "selectedIncomeOrExpenseItem", null);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Output"])()
            ], IncomeOrExpenseCategorySelectComponent.prototype, "selectedIncomeOrExpenseItemChange", void 0);
            IncomeOrExpenseCategorySelectComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-income-or-expense-category-select',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./income-or-expense-category-select.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/income-or-expense-category-select/income-or-expense-category-select.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./income-or-expense-category-select.component.css */ "./src/app/components/income-or-expense-category-select/income-or-expense-category-select.component.css")).default]
                })
            ], IncomeOrExpenseCategorySelectComponent);
            /***/ 
        }),
        /***/ "./src/app/components/income-or-expense-items-manager/income-or-expense-items-manager.component.css": 
        /*!**********************************************************************************************************!*\
          !*** ./src/app/components/income-or-expense-items-manager/income-or-expense-items-manager.component.css ***!
          \**********************************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvaW5jb21lLW9yLWV4cGVuc2UtaXRlbXMtbWFuYWdlci9pbmNvbWUtb3ItZXhwZW5zZS1pdGVtcy1tYW5hZ2VyLmNvbXBvbmVudC5jc3MifQ== */");
            /***/ 
        }),
        /***/ "./src/app/components/income-or-expense-items-manager/income-or-expense-items-manager.component.ts": 
        /*!*********************************************************************************************************!*\
          !*** ./src/app/components/income-or-expense-items-manager/income-or-expense-items-manager.component.ts ***!
          \*********************************************************************************************************/
        /*! exports provided: IncomeOrExpenseItemsManagerComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "IncomeOrExpenseItemsManagerComponent", function () { return IncomeOrExpenseItemsManagerComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/services/state-service/state.service */ "./src/app/services/state-service/state.service.ts");
            /* harmony import */ var src_app_model_income_or_expense_item_category__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! src/app/model/income-or-expense-item-category */ "./src/app/model/income-or-expense-item-category.ts");
            /* harmony import */ var src_app_model_income_or_expense_item__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! src/app/model/income-or-expense-item */ "./src/app/model/income-or-expense-item.ts");
            var IncomeOrExpenseItemsManagerComponent = /** @class */ (function () {
                function IncomeOrExpenseItemsManagerComponent(state) {
                    var _this = this;
                    this.state = state;
                    this.getItemName = (function (item) { return item.name; });
                    this.newItem = (function () {
                        var ret = new src_app_model_income_or_expense_item__WEBPACK_IMPORTED_MODULE_4__["IncomeOrExpenseItem"](0, "New Income or Expense Item");
                        ret.incomeOrExpenseItemCategoryId = _this.state.selectedIncomeOrExpenseItemCategory.id;
                        return ret;
                    });
                    this.newCategory = (function () {
                        return new src_app_model_income_or_expense_item_category__WEBPACK_IMPORTED_MODULE_3__["IncomeOrExpenseItemCategory"](0, "New Category");
                    });
                }
                IncomeOrExpenseItemsManagerComponent.prototype.ngOnInit = function () {
                    this.state.loadIncomeOrExpenseItemCategories();
                    this.state.loadIncomeOrExpenseItems();
                };
                IncomeOrExpenseItemsManagerComponent.prototype.selectItem = function (item) {
                    this.selectedItem = item;
                };
                IncomeOrExpenseItemsManagerComponent.prototype.addItem = function (item) {
                    this.selectedItem = item;
                };
                IncomeOrExpenseItemsManagerComponent.prototype.deleteItem = function (item) {
                };
                IncomeOrExpenseItemsManagerComponent.prototype.beginEditItem = function (item) {
                    this.selectedItem = item;
                };
                IncomeOrExpenseItemsManagerComponent.prototype.endEditItem = function (itemName) {
                    var _this = this;
                    this.selectedItem.name = itemName;
                    this.state.saveIncomeOrExpenseItem(this.selectedItem).subscribe(function (item) {
                        _this.selectedItem.id = item.id;
                    });
                };
                IncomeOrExpenseItemsManagerComponent.prototype.selectCategory = function (category) {
                    this.state.selectedIncomeOrExpenseItemCategory = category;
                    this.loadIncomeOrExpenseItemsForCategory();
                };
                IncomeOrExpenseItemsManagerComponent.prototype.addCategory = function (category) {
                    this.state.selectedIncomeOrExpenseItemCategory = category;
                };
                IncomeOrExpenseItemsManagerComponent.prototype.deleteCategory = function (category) {
                };
                IncomeOrExpenseItemsManagerComponent.prototype.beginEditCategory = function (category) {
                    this.state.selectedIncomeOrExpenseItemCategory = category;
                };
                IncomeOrExpenseItemsManagerComponent.prototype.endEditCategory = function (categoryName) {
                    var _this = this;
                    this.state.selectedIncomeOrExpenseItemCategory.name = categoryName;
                    this.state.saveIncomeOrExpenseItemCategory(this.state.selectedIncomeOrExpenseItemCategory).subscribe(function (ret) {
                        _this.state.selectedIncomeOrExpenseItemCategory.id = ret.id;
                    });
                };
                IncomeOrExpenseItemsManagerComponent.prototype.loadIncomeOrExpenseItemsForCategory = function () {
                    var e_3, _a;
                    this.incomeOrExpenseItems = [];
                    if (this.state.incomeOrExpenseItems) {
                        try {
                            for (var _b = __values(this.state.incomeOrExpenseItems), _c = _b.next(); !_c.done; _c = _b.next()) {
                                var itm = _c.value;
                                if (itm.incomeOrExpenseItemCategoryId == this.state.selectedIncomeOrExpenseItemCategory.id) {
                                    this.incomeOrExpenseItems.push(itm);
                                }
                            }
                        }
                        catch (e_3_1) { e_3 = { error: e_3_1 }; }
                        finally {
                            try {
                                if (_c && !_c.done && (_a = _b.return)) _a.call(_b);
                            }
                            finally { if (e_3) throw e_3.error; }
                        }
                    }
                };
                return IncomeOrExpenseItemsManagerComponent;
            }());
            IncomeOrExpenseItemsManagerComponent.ctorParameters = function () { return [
                { type: src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__["StateService"] }
            ]; };
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], IncomeOrExpenseItemsManagerComponent.prototype, "allowEditCategories", void 0);
            IncomeOrExpenseItemsManagerComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-income-or-expense-items-manager',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./income-or-expense-items-manager.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/income-or-expense-items-manager/income-or-expense-items-manager.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./income-or-expense-items-manager.component.css */ "./src/app/components/income-or-expense-items-manager/income-or-expense-items-manager.component.css")).default]
                })
            ], IncomeOrExpenseItemsManagerComponent);
            /***/ 
        }),
        /***/ "./src/app/components/money-transfer-form/money-transfer-form.component.css": 
        /*!**********************************************************************************!*\
          !*** ./src/app/components/money-transfer-form/money-transfer-form.component.css ***!
          \**********************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvbW9uZXktdHJhbnNmZXItZm9ybS9tb25leS10cmFuc2Zlci1mb3JtLmNvbXBvbmVudC5jc3MifQ== */");
            /***/ 
        }),
        /***/ "./src/app/components/money-transfer-form/money-transfer-form.component.ts": 
        /*!*********************************************************************************!*\
          !*** ./src/app/components/money-transfer-form/money-transfer-form.component.ts ***!
          \*********************************************************************************/
        /*! exports provided: MoneyTransferFormComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MoneyTransferFormComponent", function () { return MoneyTransferFormComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/services/state-service/state.service */ "./src/app/services/state-service/state.service.ts");
            /* harmony import */ var src_app_services_dialog_service_dialog_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! src/app/services/dialog-service/dialog.service */ "./src/app/services/dialog-service/dialog.service.ts");
            var MoneyTransferFormComponent = /** @class */ (function () {
                function MoneyTransferFormComponent(state, dialogService) {
                    this.state = state;
                    this.dialogService = dialogService;
                }
                MoneyTransferFormComponent.prototype.ngOnInit = function () {
                };
                MoneyTransferFormComponent.prototype.startMoneyTransfer = function () {
                    this.state.moneyTransfer(this.fromAccount.id, this.toAccount.id, this.amount);
                    this.dialogService.moneyTransferDialogServiceRef.close();
                };
                return MoneyTransferFormComponent;
            }());
            MoneyTransferFormComponent.ctorParameters = function () { return [
                { type: src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__["StateService"] },
                { type: src_app_services_dialog_service_dialog_service__WEBPACK_IMPORTED_MODULE_3__["DialogService"] }
            ]; };
            MoneyTransferFormComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-money-transfer-form',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./money-transfer-form.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/money-transfer-form/money-transfer-form.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./money-transfer-form.component.css */ "./src/app/components/money-transfer-form/money-transfer-form.component.css")).default]
                })
            ], MoneyTransferFormComponent);
            /***/ 
        }),
        /***/ "./src/app/components/monthly-balance-report/monthly-balance-report.component.css": 
        /*!****************************************************************************************!*\
          !*** ./src/app/components/monthly-balance-report/monthly-balance-report.component.css ***!
          \****************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvbW9udGhseS1iYWxhbmNlLXJlcG9ydC9tb250aGx5LWJhbGFuY2UtcmVwb3J0LmNvbXBvbmVudC5jc3MifQ== */");
            /***/ 
        }),
        /***/ "./src/app/components/monthly-balance-report/monthly-balance-report.component.ts": 
        /*!***************************************************************************************!*\
          !*** ./src/app/components/monthly-balance-report/monthly-balance-report.component.ts ***!
          \***************************************************************************************/
        /*! exports provided: MonthlyBalanceReportComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MonthlyBalanceReportComponent", function () { return MonthlyBalanceReportComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_assets_skins_dark_times_google_charts_style__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/assets/skins/dark-times/google-charts-style */ "./src/assets/skins/dark-times/google-charts-style.ts");
            var MonthlyBalanceReportComponent = /** @class */ (function () {
                function MonthlyBalanceReportComponent() {
                    this.chartData = [];
                    this.chartColumnNames = ['Month', 'Incoming', 'Outgoing', 'Balance'];
                    this.chartOptions = {};
                }
                MonthlyBalanceReportComponent.prototype.ngOnInit = function () {
                };
                MonthlyBalanceReportComponent.prototype.ngOnChanges = function () {
                    var e_4, _a;
                    this.initChartOptions();
                    if (this.sourceDate) {
                        this.chartData = [];
                        var min = 0;
                        var max = 0;
                        try {
                            for (var _b = __values(this.sourceDate), _c = _b.next(); !_c.done; _c = _b.next()) {
                                var rec = _c.value;
                                this.chartData.push([
                                    rec.recordYearMonth.toString(),
                                    rec.incomingBalance,
                                    rec.outgoingBalance,
                                    rec.remainingBalance
                                ]);
                                min = rec.incomingBalance < min ? rec.incomingBalance : min;
                                min = rec.outgoingBalance < min ? rec.outgoingBalance : min;
                                min = rec.remainingBalance < min ? rec.remainingBalance : min;
                                max = rec.incomingBalance > max ? rec.incomingBalance : max;
                                max = rec.outgoingBalance > max ? rec.outgoingBalance : max;
                                max = rec.remainingBalance > max ? rec.remainingBalance : max;
                            }
                        }
                        catch (e_4_1) { e_4 = { error: e_4_1 }; }
                        finally {
                            try {
                                if (_c && !_c.done && (_a = _b.return)) _a.call(_b);
                            }
                            finally { if (e_4) throw e_4.error; }
                        }
                        (this.chartOptions).vAxis.viewWindow.min = min;
                        (this.chartOptions).vAxis.viewWindow.max = max;
                    }
                };
                MonthlyBalanceReportComponent.prototype.initChartOptions = function () {
                    this.chartOptions = src_assets_skins_dark_times_google_charts_style__WEBPACK_IMPORTED_MODULE_2__["GoogleChartsStyle"].createOptions();
                    (this.chartOptions).seriesType = 'bars';
                    (this.chartOptions).series = { 2: { type: 'line' } };
                    (this.chartOptions).width = this.width;
                    (this.chartOptions).height = this.height;
                    if (this.hideDates) {
                        (this.chartOptions).hAxis.textPosition = 'none';
                    }
                };
                return MonthlyBalanceReportComponent;
            }());
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], MonthlyBalanceReportComponent.prototype, "sourceDate", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], MonthlyBalanceReportComponent.prototype, "width", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], MonthlyBalanceReportComponent.prototype, "height", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], MonthlyBalanceReportComponent.prototype, "hideDates", void 0);
            MonthlyBalanceReportComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-monthly-balance-report',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./monthly-balance-report.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/monthly-balance-report/monthly-balance-report.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./monthly-balance-report.component.css */ "./src/app/components/monthly-balance-report/monthly-balance-report.component.css")).default]
                })
            ], MonthlyBalanceReportComponent);
            /***/ 
        }),
        /***/ "./src/app/components/monthly-savings-report/monthly-savings-report.component.css": 
        /*!****************************************************************************************!*\
          !*** ./src/app/components/monthly-savings-report/monthly-savings-report.component.css ***!
          \****************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvbW9udGhseS1zYXZpbmdzLXJlcG9ydC9tb250aGx5LXNhdmluZ3MtcmVwb3J0LmNvbXBvbmVudC5jc3MifQ== */");
            /***/ 
        }),
        /***/ "./src/app/components/monthly-savings-report/monthly-savings-report.component.ts": 
        /*!***************************************************************************************!*\
          !*** ./src/app/components/monthly-savings-report/monthly-savings-report.component.ts ***!
          \***************************************************************************************/
        /*! exports provided: MonthlySavingsReportComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MonthlySavingsReportComponent", function () { return MonthlySavingsReportComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_assets_skins_dark_times_google_charts_style__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/assets/skins/dark-times/google-charts-style */ "./src/assets/skins/dark-times/google-charts-style.ts");
            var MonthlySavingsReportComponent = /** @class */ (function () {
                function MonthlySavingsReportComponent() {
                    this.chartData = [];
                    this.chartOptions = {};
                }
                MonthlySavingsReportComponent.prototype.ngOnInit = function () { };
                MonthlySavingsReportComponent.prototype.ngOnChanges = function () {
                    var e_5, _a;
                    this.initChartOptions();
                    if (this.sourceDate) {
                        this.chartData = [];
                        var min = null;
                        var max = null;
                        try {
                            for (var _b = __values(this.sourceDate), _c = _b.next(); !_c.done; _c = _b.next()) {
                                var rec = _c.value;
                                var val = this.sourceDataYValueMapper ? this.sourceDataYValueMapper(rec) : 0;
                                this.chartData.push([
                                    this.sourceDataXValueMapper ? this.sourceDataXValueMapper(rec) : "0",
                                    val
                                ]);
                                if (max == null) {
                                    max = val;
                                }
                                if (min == null) {
                                    min = val;
                                }
                                max = (val > max ? val : max);
                                min = (val < min ? val : min);
                            }
                        }
                        catch (e_5_1) { e_5 = { error: e_5_1 }; }
                        finally {
                            try {
                                if (_c && !_c.done && (_a = _b.return)) _a.call(_b);
                            }
                            finally { if (e_5) throw e_5.error; }
                        }
                        (this.chartOptions).vAxis.viewWindow.min = this.forceMin ? this.forceMin : min;
                        (this.chartOptions).vAxis.viewWindow.max = this.forceMax ? this.forceMax : max;
                    }
                };
                MonthlySavingsReportComponent.prototype.initChartOptions = function () {
                    this.chartOptions = src_assets_skins_dark_times_google_charts_style__WEBPACK_IMPORTED_MODULE_2__["GoogleChartsStyle"].createOptions();
                    (this.chartOptions).width = this.width;
                    (this.chartOptions).height = this.height;
                    if (this.hideDates) {
                        (this.chartOptions).hAxis.textPosition = 'none';
                    }
                };
                return MonthlySavingsReportComponent;
            }());
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], MonthlySavingsReportComponent.prototype, "sourceDate", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], MonthlySavingsReportComponent.prototype, "sourceDataXValueMapper", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], MonthlySavingsReportComponent.prototype, "sourceDataYValueMapper", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], MonthlySavingsReportComponent.prototype, "width", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], MonthlySavingsReportComponent.prototype, "height", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], MonthlySavingsReportComponent.prototype, "hideDates", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], MonthlySavingsReportComponent.prototype, "chartType", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], MonthlySavingsReportComponent.prototype, "forceMin", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], MonthlySavingsReportComponent.prototype, "forceMax", void 0);
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], MonthlySavingsReportComponent.prototype, "chartColumnNames", void 0);
            MonthlySavingsReportComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-monthly-savings-report',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./monthly-savings-report.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/monthly-savings-report/monthly-savings-report.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./monthly-savings-report.component.css */ "./src/app/components/monthly-savings-report/monthly-savings-report.component.css")).default]
                })
            ], MonthlySavingsReportComponent);
            /***/ 
        }),
        /***/ "./src/app/components/progress-bar/progress-bar.component.css": 
        /*!********************************************************************!*\
          !*** ./src/app/components/progress-bar/progress-bar.component.css ***!
          \********************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvcHJvZ3Jlc3MtYmFyL3Byb2dyZXNzLWJhci5jb21wb25lbnQuY3NzIn0= */");
            /***/ 
        }),
        /***/ "./src/app/components/progress-bar/progress-bar.component.ts": 
        /*!*******************************************************************!*\
          !*** ./src/app/components/progress-bar/progress-bar.component.ts ***!
          \*******************************************************************/
        /*! exports provided: ProgressBarComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProgressBarComponent", function () { return ProgressBarComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            var ProgressBarComponent = /** @class */ (function () {
                function ProgressBarComponent() {
                }
                ProgressBarComponent.prototype.ngOnInit = function () {
                };
                return ProgressBarComponent;
            }());
            tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])()
            ], ProgressBarComponent.prototype, "valueFromZeroToOneHundred", void 0);
            ProgressBarComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-progress-bar',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./progress-bar.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/progress-bar/progress-bar.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./progress-bar.component.css */ "./src/app/components/progress-bar/progress-bar.component.css")).default]
                })
            ], ProgressBarComponent);
            /***/ 
        }),
        /***/ "./src/app/components/section-accounts/accounts.component.css": 
        /*!********************************************************************!*\
          !*** ./src/app/components/section-accounts/accounts.component.css ***!
          \********************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvc2VjdGlvbi1hY2NvdW50cy9hY2NvdW50cy5jb21wb25lbnQuY3NzIn0= */");
            /***/ 
        }),
        /***/ "./src/app/components/section-accounts/accounts.component.ts": 
        /*!*******************************************************************!*\
          !*** ./src/app/components/section-accounts/accounts.component.ts ***!
          \*******************************************************************/
        /*! exports provided: AccountsSectionComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AccountsSectionComponent", function () { return AccountsSectionComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/services/state-service/state.service */ "./src/app/services/state-service/state.service.ts");
            /* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm2015/material.js");
            /* harmony import */ var _accounts_manager_accounts_manager_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../accounts-manager/accounts-manager.component */ "./src/app/components/accounts-manager/accounts-manager.component.ts");
            /* harmony import */ var _money_transfer_form_money_transfer_form_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../money-transfer-form/money-transfer-form.component */ "./src/app/components/money-transfer-form/money-transfer-form.component.ts");
            /* harmony import */ var src_app_services_dialog_service_dialog_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! src/app/services/dialog-service/dialog.service */ "./src/app/services/dialog-service/dialog.service.ts");
            var AccountsSectionComponent = /** @class */ (function () {
                function AccountsSectionComponent(state, dialog, dialogService) {
                    this.state = state;
                    this.dialog = dialog;
                    this.dialogService = dialogService;
                    this.sourceXValueMapper = function (rec) { return rec.recordYearMonth.toString(); };
                    this.sourceYValueMapper = function (rec) { return rec.incomingBalance + rec.outgoingBalance; };
                }
                AccountsSectionComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    this.state.loadIncomeOrExpenseItems();
                    this.state.loadAccounts().subscribe((function (ret) {
                        if (_this.state.accounts && _this.state.accounts[0]) {
                            _this.state.selectAccountId(_this.state.accounts[0].id);
                        }
                    }));
                };
                AccountsSectionComponent.prototype.showAccountsManager = function () {
                    this.dialog.open(_accounts_manager_accounts_manager_component__WEBPACK_IMPORTED_MODULE_4__["AccountsManagerComponent"], {
                        width: '300px',
                        height: '300px'
                    });
                };
                AccountsSectionComponent.prototype.showMoneyTransferForm = function () {
                    this.dialogService.moneyTransferDialogServiceRef =
                        this.dialog.open(_money_transfer_form_money_transfer_form_component__WEBPACK_IMPORTED_MODULE_5__["MoneyTransferFormComponent"], {
                            width: '340px',
                            height: '200px'
                        });
                };
                return AccountsSectionComponent;
            }());
            AccountsSectionComponent.ctorParameters = function () { return [
                { type: src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__["StateService"] },
                { type: _angular_material__WEBPACK_IMPORTED_MODULE_3__["MatDialog"] },
                { type: src_app_services_dialog_service_dialog_service__WEBPACK_IMPORTED_MODULE_6__["DialogService"] }
            ]; };
            AccountsSectionComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-section-accounts',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./accounts.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/section-accounts/accounts.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./accounts.component.css */ "./src/app/components/section-accounts/accounts.component.css")).default]
                })
            ], AccountsSectionComponent);
            /***/ 
        }),
        /***/ "./src/app/components/section-dashboard/dashboard.component.css": 
        /*!**********************************************************************!*\
          !*** ./src/app/components/section-dashboard/dashboard.component.css ***!
          \**********************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvc2VjdGlvbi1kYXNoYm9hcmQvZGFzaGJvYXJkLmNvbXBvbmVudC5jc3MifQ== */");
            /***/ 
        }),
        /***/ "./src/app/components/section-dashboard/dashboard.component.ts": 
        /*!*********************************************************************!*\
          !*** ./src/app/components/section-dashboard/dashboard.component.ts ***!
          \*********************************************************************/
        /*! exports provided: DashboardSectionComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DashboardSectionComponent", function () { return DashboardSectionComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/services/state-service/state.service */ "./src/app/services/state-service/state.service.ts");
            /* harmony import */ var src_app_model_monthly_report_simple_record__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! src/app/model/monthly-report-simple-record */ "./src/app/model/monthly-report-simple-record.ts");
            var DashboardSectionComponent = /** @class */ (function () {
                function DashboardSectionComponent(state) {
                    this.state = state;
                    this.sourceXValueMapper = function (rec) { return rec.recordYearMonth.toString(); };
                    this.sourceYValueMappers = [];
                    this.selectedAccount = null;
                    this.selectedCategory = null;
                    this.selectedItem = null;
                    this.contextItems = null;
                    this.monthlyExpensesReportChartData = null;
                    this.monthlyExpensesReportChartXValueMapper = function (rec) { return rec.month; };
                    this.monthlyExpensesReportChartYValueMapper = function (rec) { return rec.value; };
                }
                DashboardSectionComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    this.state.loadIncomeOrExpenseItems();
                    this.state.loadIncomeOrExpenseItemCategories();
                    this.state.loadMonthlyExpensesReports();
                    this.state.loadMonitoredCurrency().subscribe(function (mnCr) {
                        var e_6, _a;
                        var _loop_1 = function (mc) {
                            _this.sourceYValueMappers[mc.currencyTypeName.valueOf()]
                                = function (rec) { return rec.remainingBalance / mc.lastCollectedValue.valueOf(); };
                        };
                        try {
                            for (var mnCr_1 = __values(mnCr), mnCr_1_1 = mnCr_1.next(); !mnCr_1_1.done; mnCr_1_1 = mnCr_1.next()) {
                                var mc = mnCr_1_1.value;
                                _loop_1(mc);
                            }
                        }
                        catch (e_6_1) { e_6 = { error: e_6_1 }; }
                        finally {
                            try {
                                if (mnCr_1_1 && !mnCr_1_1.done && (_a = mnCr_1.return)) _a.call(mnCr_1);
                            }
                            finally { if (e_6) throw e_6.error; }
                        }
                    });
                    this.state.loadDeposits();
                    this.state.loadSummaryOfAllAccounts();
                };
                DashboardSectionComponent.prototype.getAccountValue = function (accId) {
                    var acSum = this.state.accountSummaries[accId.valueOf()];
                    return acSum.runningSumIncoming + acSum.runningSumOutgoing;
                };
                DashboardSectionComponent.prototype.onAccountChanged = function () {
                    this.monthlyExpensesReportChartData = null;
                    this.computeMonthlyExpensesReportChartData();
                };
                DashboardSectionComponent.prototype.onCategoryChanged = function () {
                    this.monthlyExpensesReportChartData = null;
                    this.contextItems = null;
                    this.selectedItem = null;
                    this.computeMonthlyExpensesReportChartData();
                };
                DashboardSectionComponent.prototype.onItemChanged = function () {
                    this.monthlyExpensesReportChartData = null;
                    this.computeMonthlyExpensesReportChartData();
                };
                DashboardSectionComponent.prototype.isItemValidInContext = function (item) {
                    return (!(this.selectedCategory)) || item.incomeOrExpenseItemCategoryId == this.selectedCategory.id;
                };
                DashboardSectionComponent.prototype.getValidIncomeOrExpenseItems = function () {
                    var e_7, _a;
                    if (this.contextItems) {
                        return this.contextItems;
                    }
                    var ret = [];
                    try {
                        for (var _b = __values(this.state.incomeOrExpenseItems), _c = _b.next(); !_c.done; _c = _b.next()) {
                            var itm = _c.value;
                            if (this.isItemValidInContext(itm)) {
                                ret.push(itm);
                            }
                        }
                    }
                    catch (e_7_1) { e_7 = { error: e_7_1 }; }
                    finally {
                        try {
                            if (_c && !_c.done && (_a = _b.return)) _a.call(_b);
                        }
                        finally { if (e_7) throw e_7.error; }
                    }
                    return ret;
                };
                DashboardSectionComponent.prototype.computeMonthlyExpensesReportChartData = function () {
                    var e_8, _a;
                    this.monthlyExpensesReportChartData = [];
                    if (this.selectedAccount) {
                        this.addMonthlyExpensesReportDataForAccount(this.selectedAccount.id);
                    }
                    else {
                        try {
                            for (var _b = __values(this.state.accounts), _c = _b.next(); !_c.done; _c = _b.next()) {
                                var acct = _c.value;
                                this.addMonthlyExpensesReportDataForAccount(acct.id);
                            }
                        }
                        catch (e_8_1) { e_8 = { error: e_8_1 }; }
                        finally {
                            try {
                                if (_c && !_c.done && (_a = _b.return)) _a.call(_b);
                            }
                            finally { if (e_8) throw e_8.error; }
                        }
                    }
                };
                DashboardSectionComponent.prototype.addMonthlyExpensesReportDataForAccount = function (accountId) {
                    var e_9, _a, e_10, _b, e_11, _c;
                    var acctReport = this.findMonthlyExpensesAccountReport(accountId);
                    if (acctReport) {
                        try {
                            for (var _d = __values(acctReport.months), _e = _d.next(); !_e.done; _e = _d.next()) {
                                var month = _e.value;
                                var repMonth = this.findReportMonthRecord(month.monthNumber);
                                try {
                                    for (var _f = (e_10 = void 0, __values(month.categories)), _g = _f.next(); !_g.done; _g = _f.next()) {
                                        var cat = _g.value;
                                        if (!(this.selectedCategory) || this.selectedCategory.id == cat.categoryId) {
                                            try {
                                                for (var _h = (e_11 = void 0, __values(cat.items)), _j = _h.next(); !_j.done; _j = _h.next()) {
                                                    var itm = _j.value;
                                                    if (!(this.selectedItem) || this.selectedItem.id == itm.itemId) {
                                                        if (repMonth.value) {
                                                            repMonth.value += itm.totalExpenses.valueOf();
                                                        }
                                                        else {
                                                            repMonth.value = itm.totalExpenses.valueOf();
                                                        }
                                                    }
                                                }
                                            }
                                            catch (e_11_1) { e_11 = { error: e_11_1 }; }
                                            finally {
                                                try {
                                                    if (_j && !_j.done && (_c = _h.return)) _c.call(_h);
                                                }
                                                finally { if (e_11) throw e_11.error; }
                                            }
                                        }
                                    }
                                }
                                catch (e_10_1) { e_10 = { error: e_10_1 }; }
                                finally {
                                    try {
                                        if (_g && !_g.done && (_b = _f.return)) _b.call(_f);
                                    }
                                    finally { if (e_10) throw e_10.error; }
                                }
                            }
                        }
                        catch (e_9_1) { e_9 = { error: e_9_1 }; }
                        finally {
                            try {
                                if (_e && !_e.done && (_a = _d.return)) _a.call(_d);
                            }
                            finally { if (e_9) throw e_9.error; }
                        }
                    }
                };
                DashboardSectionComponent.prototype.findMonthlyExpensesAccountReport = function (accountId) {
                    var e_12, _a;
                    try {
                        for (var _b = __values(this.state.monthlyExpensesReports), _c = _b.next(); !_c.done; _c = _b.next()) {
                            var rep = _c.value;
                            if (rep.accountId == accountId) {
                                return rep;
                            }
                        }
                    }
                    catch (e_12_1) { e_12 = { error: e_12_1 }; }
                    finally {
                        try {
                            if (_c && !_c.done && (_a = _b.return)) _a.call(_b);
                        }
                        finally { if (e_12) throw e_12.error; }
                    }
                    return null;
                };
                DashboardSectionComponent.prototype.findReportMonthRecord = function (monthNumber) {
                    var e_13, _a;
                    try {
                        for (var _b = __values(this.monthlyExpensesReportChartData), _c = _b.next(); !_c.done; _c = _b.next()) {
                            var rec_1 = _c.value;
                            if (rec_1.month == monthNumber.toString()) {
                                return rec_1;
                            }
                        }
                    }
                    catch (e_13_1) { e_13 = { error: e_13_1 }; }
                    finally {
                        try {
                            if (_c && !_c.done && (_a = _b.return)) _a.call(_b);
                        }
                        finally { if (e_13) throw e_13.error; }
                    }
                    var rec = new src_app_model_monthly_report_simple_record__WEBPACK_IMPORTED_MODULE_3__["MonthlyReportSimpleRecord"]();
                    rec.month = monthNumber.toString();
                    rec.value = 0;
                    this.monthlyExpensesReportChartData.push(rec);
                    return rec;
                };
                return DashboardSectionComponent;
            }());
            DashboardSectionComponent.ctorParameters = function () { return [
                { type: src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__["StateService"] }
            ]; };
            DashboardSectionComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-section-dashboard',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./dashboard.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/section-dashboard/dashboard.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./dashboard.component.css */ "./src/app/components/section-dashboard/dashboard.component.css")).default]
                })
            ], DashboardSectionComponent);
            /***/ 
        }),
        /***/ "./src/app/components/section-deposits/deposits.component.css": 
        /*!********************************************************************!*\
          !*** ./src/app/components/section-deposits/deposits.component.css ***!
          \********************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvc2VjdGlvbi1kZXBvc2l0cy9kZXBvc2l0cy5jb21wb25lbnQuY3NzIn0= */");
            /***/ 
        }),
        /***/ "./src/app/components/section-deposits/deposits.component.ts": 
        /*!*******************************************************************!*\
          !*** ./src/app/components/section-deposits/deposits.component.ts ***!
          \*******************************************************************/
        /*! exports provided: DepositsSectionComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DepositsSectionComponent", function () { return DepositsSectionComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/services/state-service/state.service */ "./src/app/services/state-service/state.service.ts");
            var DepositsSectionComponent = /** @class */ (function () {
                function DepositsSectionComponent(state) {
                    this.state = state;
                }
                DepositsSectionComponent.prototype.ngOnInit = function () {
                    this.state.deposits = null;
                    this.state.loadDeposits();
                    this.state.selectedDeposit = null;
                };
                DepositsSectionComponent.prototype.getTotalDepositsValue = function () {
                    return this.state.totalDepositsValue;
                };
                DepositsSectionComponent.prototype.getTotalDepositsInterest = function () {
                    return this.state.totalDepositsInterest;
                };
                DepositsSectionComponent.prototype.getFirstDeposit = function () {
                    return this.state.firstDepositToReachMaturity;
                };
                return DepositsSectionComponent;
            }());
            DepositsSectionComponent.ctorParameters = function () { return [
                { type: src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__["StateService"] }
            ]; };
            DepositsSectionComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-section-deposits',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./deposits.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/section-deposits/deposits.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./deposits.component.css */ "./src/app/components/section-deposits/deposits.component.css")).default]
                })
            ], DepositsSectionComponent);
            /***/ 
        }),
        /***/ "./src/app/components/section-exchange-rates/section-exchange-rates.component.css": 
        /*!****************************************************************************************!*\
          !*** ./src/app/components/section-exchange-rates/section-exchange-rates.component.css ***!
          \****************************************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvc2VjdGlvbi1leGNoYW5nZS1yYXRlcy9zZWN0aW9uLWV4Y2hhbmdlLXJhdGVzLmNvbXBvbmVudC5jc3MifQ== */");
            /***/ 
        }),
        /***/ "./src/app/components/section-exchange-rates/section-exchange-rates.component.ts": 
        /*!***************************************************************************************!*\
          !*** ./src/app/components/section-exchange-rates/section-exchange-rates.component.ts ***!
          \***************************************************************************************/
        /*! exports provided: SectionExchangeRatesComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SectionExchangeRatesComponent", function () { return SectionExchangeRatesComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/services/state-service/state.service */ "./src/app/services/state-service/state.service.ts");
            var SectionExchangeRatesComponent = /** @class */ (function () {
                function SectionExchangeRatesComponent(state) {
                    this.state = state;
                    this.currencies = [];
                    this.chartData = [];
                    this.sourceXValueMapper = function (rec) { return rec.date.toString().split("T")[0]; };
                    this.sourceYValueMapper = function (rec) { return rec.value; };
                }
                SectionExchangeRatesComponent.prototype.ngOnInit = function () {
                    this.state.loadExchangeRatesHistory();
                };
                SectionExchangeRatesComponent.prototype.getCurrencies = function () {
                    var _this = this;
                    if (this.currencies.length == 0) {
                        this.state.exchageRatesHistory.forEach(function (val, key) {
                            _this.currencies.push(key.valueOf());
                        });
                    }
                    return this.currencies;
                };
                SectionExchangeRatesComponent.prototype.getChartData = function (crName) {
                    var ret = this.chartData[crName];
                    if (!ret) {
                        ret = this.state.exchageRatesHistory.get(crName);
                        this.chartData.push(ret);
                    }
                    return ret;
                };
                return SectionExchangeRatesComponent;
            }());
            SectionExchangeRatesComponent.ctorParameters = function () { return [
                { type: src_app_services_state_service_state_service__WEBPACK_IMPORTED_MODULE_2__["StateService"] }
            ]; };
            SectionExchangeRatesComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-section-exchange-rates',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./section-exchange-rates.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/section-exchange-rates/section-exchange-rates.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./section-exchange-rates.component.css */ "./src/app/components/section-exchange-rates/section-exchange-rates.component.css")).default]
                })
            ], SectionExchangeRatesComponent);
            /***/ 
        }),
        /***/ "./src/app/components/section-main/main.component.css": 
        /*!************************************************************!*\
          !*** ./src/app/components/section-main/main.component.css ***!
          \************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvc2VjdGlvbi1tYWluL21haW4uY29tcG9uZW50LmNzcyJ9 */");
            /***/ 
        }),
        /***/ "./src/app/components/section-main/main.component.ts": 
        /*!***********************************************************!*\
          !*** ./src/app/components/section-main/main.component.ts ***!
          \***********************************************************/
        /*! exports provided: MainSectionComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MainSectionComponent", function () { return MainSectionComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            var MainSectionComponent = /** @class */ (function () {
                function MainSectionComponent() {
                }
                MainSectionComponent.prototype.ngOnInit = function () {
                };
                return MainSectionComponent;
            }());
            MainSectionComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-section-main',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./main.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/section-main/main.component.html")).default,
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./main.component.css */ "./src/app/components/section-main/main.component.css")).default]
                })
            ], MainSectionComponent);
            /***/ 
        }),
        /***/ "./src/app/components/sliding-menu/sliding-menu.component.css": 
        /*!********************************************************************!*\
          !*** ./src/app/components/sliding-menu/sliding-menu.component.css ***!
          \********************************************************************/
        /*! exports provided: default */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvc2xpZGluZy1tZW51L3NsaWRpbmctbWVudS5jb21wb25lbnQuY3NzIn0= */");
            /***/ 
        }),
        /***/ "./src/app/components/sliding-menu/sliding-menu.component.ts": 
        /*!*******************************************************************!*\
          !*** ./src/app/components/sliding-menu/sliding-menu.component.ts ***!
          \*******************************************************************/
        /*! exports provided: SlidingMenuComponent */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SlidingMenuComponent", function () { return SlidingMenuComponent; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_services_navigation_service_navigation_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/services/navigation-service/navigation.service */ "./src/app/services/navigation-service/navigation.service.ts");
            /* harmony import */ var _angular_animations__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/animations */ "./node_modules/@angular/animations/fesm2015/animations.js");
            var SlidingMenuComponent = /** @class */ (function () {
                function SlidingMenuComponent(navigation) {
                    this.navigation = navigation;
                    this.currentState = 'up';
                }
                SlidingMenuComponent.prototype.ngOnInit = function () {
                };
                SlidingMenuComponent.prototype.onMouseOver = function () {
                    this.currentState = 'down';
                };
                SlidingMenuComponent.prototype.onMouseOut = function () {
                    this.currentState = 'up';
                };
                return SlidingMenuComponent;
            }());
            SlidingMenuComponent.ctorParameters = function () { return [
                { type: src_app_services_navigation_service_navigation_service__WEBPACK_IMPORTED_MODULE_2__["NavigationService"] }
            ]; };
            SlidingMenuComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
                    selector: 'app-sliding-menu',
                    template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./sliding-menu.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/components/sliding-menu/sliding-menu.component.html")).default,
                    animations: [
                        Object(_angular_animations__WEBPACK_IMPORTED_MODULE_3__["trigger"])('pullMenu', [
                            Object(_angular_animations__WEBPACK_IMPORTED_MODULE_3__["state"])('up', Object(_angular_animations__WEBPACK_IMPORTED_MODULE_3__["style"])({ top: '-67px' })),
                            Object(_angular_animations__WEBPACK_IMPORTED_MODULE_3__["state"])('down', Object(_angular_animations__WEBPACK_IMPORTED_MODULE_3__["style"])({ top: '-2px' })),
                            Object(_angular_animations__WEBPACK_IMPORTED_MODULE_3__["transition"])('down=>up', Object(_angular_animations__WEBPACK_IMPORTED_MODULE_3__["animate"])('300ms')),
                            Object(_angular_animations__WEBPACK_IMPORTED_MODULE_3__["transition"])('up=>down', Object(_angular_animations__WEBPACK_IMPORTED_MODULE_3__["animate"])('100ms'))
                        ]),
                    ],
                    styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./sliding-menu.component.css */ "./src/app/components/sliding-menu/sliding-menu.component.css")).default]
                })
            ], SlidingMenuComponent);
            /***/ 
        }),
        /***/ "./src/app/model/account-record.ts": 
        /*!*****************************************!*\
          !*** ./src/app/model/account-record.ts ***!
          \*****************************************/
        /*! exports provided: AccountRecord */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AccountRecord", function () { return AccountRecord; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _identifiable__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./identifiable */ "./src/app/model/identifiable.ts");
            var AccountRecord = /** @class */ (function (_super) {
                __extends(AccountRecord, _super);
                function AccountRecord() {
                    return _super !== null && _super.apply(this, arguments) || this;
                }
                return AccountRecord;
            }(_identifiable__WEBPACK_IMPORTED_MODULE_1__["Identifiable"]));
            /***/ 
        }),
        /***/ "./src/app/model/account.ts": 
        /*!**********************************!*\
          !*** ./src/app/model/account.ts ***!
          \**********************************/
        /*! exports provided: Account */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Account", function () { return Account; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _identifiable__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./identifiable */ "./src/app/model/identifiable.ts");
            var Account = /** @class */ (function (_super) {
                __extends(Account, _super);
                function Account(id, name) {
                    var _this = _super.call(this, id) || this;
                    _this.name = name;
                    return _this;
                }
                return Account;
            }(_identifiable__WEBPACK_IMPORTED_MODULE_1__["Identifiable"]));
            /***/ 
        }),
        /***/ "./src/app/model/bank.ts": 
        /*!*******************************!*\
          !*** ./src/app/model/bank.ts ***!
          \*******************************/
        /*! exports provided: Bank */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Bank", function () { return Bank; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _nameable__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./nameable */ "./src/app/model/nameable.ts");
            var Bank = /** @class */ (function (_super) {
                __extends(Bank, _super);
                function Bank(id, name) {
                    return _super.call(this, id, name) || this;
                }
                return Bank;
            }(_nameable__WEBPACK_IMPORTED_MODULE_1__["Nameable"]));
            /***/ 
        }),
        /***/ "./src/app/model/deposit.ts": 
        /*!**********************************!*\
          !*** ./src/app/model/deposit.ts ***!
          \**********************************/
        /*! exports provided: Deposit */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Deposit", function () { return Deposit; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _identifiable__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./identifiable */ "./src/app/model/identifiable.ts");
            var Deposit = /** @class */ (function (_super) {
                __extends(Deposit, _super);
                function Deposit() {
                    return _super !== null && _super.apply(this, arguments) || this;
                }
                return Deposit;
            }(_identifiable__WEBPACK_IMPORTED_MODULE_1__["Identifiable"]));
            /***/ 
        }),
        /***/ "./src/app/model/identifiable.ts": 
        /*!***************************************!*\
          !*** ./src/app/model/identifiable.ts ***!
          \***************************************/
        /*! exports provided: Identifiable */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Identifiable", function () { return Identifiable; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            var Identifiable = /** @class */ (function () {
                function Identifiable(id) {
                    this.id = id;
                }
                return Identifiable;
            }());
            /***/ 
        }),
        /***/ "./src/app/model/income-or-expense-item-category.ts": 
        /*!**********************************************************!*\
          !*** ./src/app/model/income-or-expense-item-category.ts ***!
          \**********************************************************/
        /*! exports provided: IncomeOrExpenseItemCategory */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "IncomeOrExpenseItemCategory", function () { return IncomeOrExpenseItemCategory; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _nameable__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./nameable */ "./src/app/model/nameable.ts");
            var IncomeOrExpenseItemCategory = /** @class */ (function (_super) {
                __extends(IncomeOrExpenseItemCategory, _super);
                function IncomeOrExpenseItemCategory() {
                    return _super !== null && _super.apply(this, arguments) || this;
                }
                return IncomeOrExpenseItemCategory;
            }(_nameable__WEBPACK_IMPORTED_MODULE_1__["Nameable"]));
            /***/ 
        }),
        /***/ "./src/app/model/income-or-expense-item.ts": 
        /*!*************************************************!*\
          !*** ./src/app/model/income-or-expense-item.ts ***!
          \*************************************************/
        /*! exports provided: IncomeOrExpenseItem */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "IncomeOrExpenseItem", function () { return IncomeOrExpenseItem; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _nameable__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./nameable */ "./src/app/model/nameable.ts");
            var IncomeOrExpenseItem = /** @class */ (function (_super) {
                __extends(IncomeOrExpenseItem, _super);
                function IncomeOrExpenseItem() {
                    return _super !== null && _super.apply(this, arguments) || this;
                }
                return IncomeOrExpenseItem;
            }(_nameable__WEBPACK_IMPORTED_MODULE_1__["Nameable"]));
            /***/ 
        }),
        /***/ "./src/app/model/monthly-balance-report-record.ts": 
        /*!********************************************************!*\
          !*** ./src/app/model/monthly-balance-report-record.ts ***!
          \********************************************************/
        /*! exports provided: MonthlyBalanceReportRecord */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MonthlyBalanceReportRecord", function () { return MonthlyBalanceReportRecord; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            var MonthlyBalanceReportRecord = /** @class */ (function () {
                function MonthlyBalanceReportRecord(recordYearMonth, startingBalance, incomingBalance, outgoingBalance) {
                    this.recordYearMonth = recordYearMonth;
                    this.startingBalance = startingBalance;
                    this.incomingBalance = incomingBalance;
                    this.outgoingBalance = outgoingBalance;
                    this.remainingBalance = this.startingBalance + this.incomingBalance + this.outgoingBalance;
                }
                return MonthlyBalanceReportRecord;
            }());
            /***/ 
        }),
        /***/ "./src/app/model/monthly-report-simple-record.ts": 
        /*!*******************************************************!*\
          !*** ./src/app/model/monthly-report-simple-record.ts ***!
          \*******************************************************/
        /*! exports provided: MonthlyReportSimpleRecord */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MonthlyReportSimpleRecord", function () { return MonthlyReportSimpleRecord; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            var MonthlyReportSimpleRecord = /** @class */ (function () {
                function MonthlyReportSimpleRecord() {
                }
                return MonthlyReportSimpleRecord;
            }());
            /***/ 
        }),
        /***/ "./src/app/model/nameable.ts": 
        /*!***********************************!*\
          !*** ./src/app/model/nameable.ts ***!
          \***********************************/
        /*! exports provided: Nameable */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Nameable", function () { return Nameable; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _identifiable__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./identifiable */ "./src/app/model/identifiable.ts");
            var Nameable = /** @class */ (function (_super) {
                __extends(Nameable, _super);
                function Nameable(id, name) {
                    var _this = _super.call(this, id) || this;
                    _this.name = name;
                    return _this;
                }
                return Nameable;
            }(_identifiable__WEBPACK_IMPORTED_MODULE_1__["Identifiable"]));
            /***/ 
        }),
        /***/ "./src/app/pipes/iso-date.pipe.ts": 
        /*!****************************************!*\
          !*** ./src/app/pipes/iso-date.pipe.ts ***!
          \****************************************/
        /*! exports provided: IsoDatePipe */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "IsoDatePipe", function () { return IsoDatePipe; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            var IsoDatePipe = /** @class */ (function () {
                function IsoDatePipe() {
                }
                IsoDatePipe.prototype.transform = function (value) {
                    var args = [];
                    for (var _i = 1; _i < arguments.length; _i++) {
                        args[_i - 1] = arguments[_i];
                    }
                    return new Date(value).toISOString().split("T")[0];
                };
                return IsoDatePipe;
            }());
            IsoDatePipe = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Pipe"])({
                    name: 'isoDate'
                })
            ], IsoDatePipe);
            /***/ 
        }),
        /***/ "./src/app/reusable/dom.ops.ts": 
        /*!*************************************!*\
          !*** ./src/app/reusable/dom.ops.ts ***!
          \*************************************/
        /*! exports provided: DomOps */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DomOps", function () { return DomOps; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            var DomOps = /** @class */ (function () {
                function DomOps() {
                }
                DomOps.getTagByTypeAndName = function (tagType, tagNameAttributeValue) {
                    var links = document.getElementsByTagName(tagType);
                    for (var l = 0; l < links.length; l++) {
                        var link = links[l];
                        if (link.getAttribute("name") == tagNameAttributeValue) {
                            return link;
                        }
                    }
                };
                return DomOps;
            }());
            /***/ 
        }),
        /***/ "./src/app/reusable/formats.ts": 
        /*!*************************************!*\
          !*** ./src/app/reusable/formats.ts ***!
          \*************************************/
        /*! exports provided: MY_FORMATS */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MY_FORMATS", function () { return MY_FORMATS; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            var MY_FORMATS = {
                parse: {
                    dateInput: 'LL',
                },
                display: {
                    dateInput: 'YYYY-MM-DD',
                    monthYearLabel: 'YYYY',
                    dateA11yLabel: 'LL',
                    monthYearA11yLabel: 'YYYY',
                },
            };
            /***/ 
        }),
        /***/ "./src/app/reusable/navigation.ts": 
        /*!****************************************!*\
          !*** ./src/app/reusable/navigation.ts ***!
          \****************************************/
        /*! exports provided: NavigationSection, Navigation */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "NavigationSection", function () { return NavigationSection; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Navigation", function () { return Navigation; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            var NavigationSection = /** @class */ (function () {
                function NavigationSection(id, name, historySkipFlag, nBackSteps, iconCssClass) {
                    this.id = id;
                    this.historySkipFlag = historySkipFlag;
                    this.nBackSteps = nBackSteps;
                    this.name = name;
                    this.iconCssClass = iconCssClass;
                }
                return NavigationSection;
            }());
            var Navigation = /** @class */ (function () {
                function Navigation() {
                    this.sections = [];
                    this.currentSectionId = 0;
                    this.history = [];
                }
                Navigation.prototype.withSectionNameAndIcon = function (sectionId, sectionName, iconCssClass) {
                    var navSection = new NavigationSection(sectionId, sectionName, null, null, iconCssClass);
                    this.sections[sectionId] = navSection;
                    return this;
                };
                Navigation.prototype.startingAtSection = function (sectionId) {
                    this.currentSectionId = sectionId;
                    return this;
                };
                Navigation.prototype.skippingSectionIdFromHistory = function (sectionId) {
                    this.sections[sectionId].historySkipFlag = 1;
                    return this;
                };
                Navigation.prototype.notSkippingSectionIdFromHistory = function (sectionId) {
                    this.sections[sectionId].historySkipFlag = 0;
                    return this;
                };
                Navigation.prototype.poppingStepsFromHistoryOnSectionGoBack = function (sectionId, nStepsToPop) {
                    this.sections[sectionId].nBackSteps = nStepsToPop;
                    return this;
                };
                Navigation.prototype.navigateToSectionId = function (sectionId) {
                    this.putSectionIdIntoHistory(this.currentSectionId);
                    this.currentSectionId = sectionId;
                };
                Navigation.prototype.navigateBack = function () {
                    var nSectionsToPop = this.sections[this.currentSectionId].nBackSteps;
                    if (nSectionsToPop == null || nSectionsToPop == undefined) {
                        nSectionsToPop = 1;
                    }
                    for (var n = 0; n < nSectionsToPop; n++) {
                        if (this.history.length > 0) {
                            this.currentSectionId = this.history.pop();
                        }
                    }
                };
                Navigation.prototype.putSectionIdIntoHistory = function (sectionId) {
                    if (this.sections[sectionId].historySkipFlag == 1) {
                        return;
                    }
                    this.history.push(sectionId);
                };
                Navigation.prototype.getCurrentSection = function () {
                    return this.sections[this.currentSectionId];
                };
                Navigation.prototype.getSections = function () {
                    return this.sections;
                };
                return Navigation;
            }());
            /***/ 
        }),
        /***/ "./src/app/reusable/params.builder.ts": 
        /*!********************************************!*\
          !*** ./src/app/reusable/params.builder.ts ***!
          \********************************************/
        /*! exports provided: ParamsBuilder */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ParamsBuilder", function () { return ParamsBuilder; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            var ParamsBuilder = /** @class */ (function () {
                function ParamsBuilder() {
                    this.params = new Map();
                }
                ParamsBuilder.prototype.withStringParam = function (key, value) {
                    this.params.set(key, value.valueOf());
                    return this;
                };
                ParamsBuilder.prototype.withNumberParam = function (key, value) {
                    this.params.set(key, value.valueOf().toString());
                    var n = 0;
                    return this;
                };
                ParamsBuilder.prototype.build = function () {
                    return this.params;
                };
                return ParamsBuilder;
            }());
            /***/ 
        }),
        /***/ "./src/app/services/configuration-service/configuration.service.ts": 
        /*!*************************************************************************!*\
          !*** ./src/app/services/configuration-service/configuration.service.ts ***!
          \*************************************************************************/
        /*! exports provided: ConfigurationService */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ConfigurationService", function () { return ConfigurationService; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            var ConfigurationService = /** @class */ (function () {
                function ConfigurationService() {
                }
                ConfigurationService.prototype.getAttributeValue = function (attributeName) {
                    var confElmArray = document.getElementsByTagName("configuration");
                    if (confElmArray) {
                        var config = confElmArray[0];
                        if (config) {
                            return config.getAttribute(attributeName);
                        }
                    }
                    throw "index.html is missing the <configuration> tag";
                };
                return ConfigurationService;
            }());
            ConfigurationService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
                    providedIn: 'root'
                })
            ], ConfigurationService);
            /***/ 
        }),
        /***/ "./src/app/services/data-service/data.service.ts": 
        /*!*******************************************************!*\
          !*** ./src/app/services/data-service/data.service.ts ***!
          \*******************************************************/
        /*! exports provided: DataService */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DataService", function () { return DataService; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var _http_client_wrapper_http_client_wrapper_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../http-client-wrapper/http-client-wrapper.service */ "./src/app/services/http-client-wrapper/http-client-wrapper.service.ts");
            /* harmony import */ var _configuration_service_configuration_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../configuration-service/configuration.service */ "./src/app/services/configuration-service/configuration.service.ts");
            var DataService = /** @class */ (function () {
                function DataService(httpClientWrapper, configuration) {
                    this.httpClientWrapper = httpClientWrapper;
                    this.configuration = configuration;
                    this.httpClientWrapper.setBaseUrl(configuration.getAttributeValue("backendUrl"));
                }
                DataService.prototype.list = function (basePath) {
                    return this.httpClientWrapper.request(_http_client_wrapper_http_client_wrapper_service__WEBPACK_IMPORTED_MODULE_2__["RequestType"].GET, basePath + "/list", null);
                };
                DataService.prototype.save = function (basePath, item, customOperation) {
                    if (customOperation === void 0) { customOperation = null; }
                    return this.httpClientWrapper.request(_http_client_wrapper_http_client_wrapper_service__WEBPACK_IMPORTED_MODULE_2__["RequestType"].POST, basePath + (customOperation ? ("/" + customOperation) : "/save"), item);
                };
                DataService.prototype.delete = function (basePath, item) {
                    return this.httpClientWrapper.request(_http_client_wrapper_http_client_wrapper_service__WEBPACK_IMPORTED_MODULE_2__["RequestType"].DELETE, basePath + "/delete", null, new Map([["id", item.id.toFixed()]]));
                };
                DataService.prototype.get = function (url, params) {
                    return this.httpClientWrapper.request(_http_client_wrapper_http_client_wrapper_service__WEBPACK_IMPORTED_MODULE_2__["RequestType"].GET, url, null, params);
                };
                DataService.prototype.post = function (url, params) {
                    return this.httpClientWrapper.request(_http_client_wrapper_http_client_wrapper_service__WEBPACK_IMPORTED_MODULE_2__["RequestType"].POST, url, null, params);
                };
                return DataService;
            }());
            DataService.ctorParameters = function () { return [
                { type: _http_client_wrapper_http_client_wrapper_service__WEBPACK_IMPORTED_MODULE_2__["HttpClientWrapperService"] },
                { type: _configuration_service_configuration_service__WEBPACK_IMPORTED_MODULE_3__["ConfigurationService"] }
            ]; };
            DataService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
                    providedIn: 'root'
                })
            ], DataService);
            /***/ 
        }),
        /***/ "./src/app/services/dialog-service/dialog.service.ts": 
        /*!***********************************************************!*\
          !*** ./src/app/services/dialog-service/dialog.service.ts ***!
          \***********************************************************/
        /*! exports provided: DialogService */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DialogService", function () { return DialogService; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm2015/index.js");
            var DialogService = /** @class */ (function () {
                function DialogService() {
                    this.dialogYesNoResponseSubject = new rxjs__WEBPACK_IMPORTED_MODULE_2__["Subject"]();
                    this.dialogYesNoResponseObservable = this.dialogYesNoResponseSubject.asObservable();
                }
                DialogService.prototype.closeErrDialog = function () {
                    this.errDialogRef.close();
                    this.dialogMessage = "";
                };
                DialogService.prototype.closeYesNoDialog = function (answer) {
                    this.yesNoDialogRef.close();
                    this.dialogMessage = "";
                    this.dialogYesNoResponseSubject.next(answer);
                    // Make sure other services only subscribe once to this thing
                    this.dialogYesNoResponseSubject = new rxjs__WEBPACK_IMPORTED_MODULE_2__["Subject"]();
                    this.dialogYesNoResponseObservable = this.dialogYesNoResponseSubject.asObservable();
                };
                return DialogService;
            }());
            DialogService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
                    providedIn: 'root'
                })
            ], DialogService);
            /***/ 
        }),
        /***/ "./src/app/services/http-client-wrapper/http-client-wrapper.service.ts": 
        /*!*****************************************************************************!*\
          !*** ./src/app/services/http-client-wrapper/http-client-wrapper.service.ts ***!
          \*****************************************************************************/
        /*! exports provided: RequestType, ResponseType, HttpClientWrapperService */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "RequestType", function () { return RequestType; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ResponseType", function () { return ResponseType; });
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "HttpClientWrapperService", function () { return HttpClientWrapperService; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm2015/http.js");
            /* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm2015/index.js");
            /* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ "./node_modules/rxjs/_esm2015/operators/index.js");
            /**
             * used by the request() method
             */
            var RequestType;
            (function (RequestType) {
                RequestType["POST"] = "POST";
                RequestType["GET"] = "GET";
                RequestType["DELETE"] = "DELETE";
                RequestType["PUT"] = "PUT";
                RequestType["HEAD"] = "HEAD";
                RequestType["JSONP"] = "JSONP";
                RequestType["OPTIONS"] = "OPTIONS";
                RequestType["PATCH"] = "PATCH";
            })(RequestType || (RequestType = {}));
            /**
             * used by the request() method
             */
            var ResponseType;
            (function (ResponseType) {
                ResponseType["ARRAYBUFFER"] = "arraybuffer";
                ResponseType["TEXT"] = "text";
                ResponseType["BLOB"] = "blob";
                ResponseType["JSON"] = "json";
            })(ResponseType || (ResponseType = {}));
            /**
             * Wrapper over some of the HttpClient's functionality, to make the calls easier and shorter
             */
            // TODO: Consider creating a builder - depends on the complexity of the project
            var HttpClientWrapperService = /** @class */ (function () {
                function HttpClientWrapperService(httpClient) {
                    this.httpClient = httpClient;
                }
                HttpClientWrapperService.prototype.setBaseUrl = function (val) {
                    this.baseUrl = val;
                };
                HttpClientWrapperService.prototype.getBaseUrl = function () {
                    return this.baseUrl;
                };
                /**
                 * Simplified version of the request from the HttpClient,
                 * designed for in-line calls instead of having to instantiate headers and other things
                 */
                HttpClientWrapperService.prototype.request = function (requestType, url, body, params, headers, responseType) {
                    // Workaround to allow the parameters to be passed to the constructor of HttpRequest
                    var requestTypeForced = requestType.valueOf();
                    var responseTypeForced = responseType == null ? 'json' : responseType.valueOf();
                    // Build the params (if any)
                    var httpParams = null;
                    if (params != null) {
                        httpParams = new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpParams"]();
                        params.forEach(function (value, key) {
                            httpParams = httpParams.append(key, value);
                        });
                    }
                    // Build the headers (if any)
                    var httpHeaders = null;
                    if (headers != null) {
                        httpHeaders = new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpHeaders"]();
                        headers.forEach(function (value, key) {
                            httpHeaders = httpHeaders.append(key, value);
                        });
                    }
                    // Send the request
                    return this.httpClient.request(requestTypeForced, this.baseUrl + '/' + url, {
                        body: body,
                        headers: httpHeaders,
                        observe: 'body',
                        params: httpParams,
                        responseType: responseTypeForced,
                        reportProgress: false,
                        withCredentials: true
                    })
                        .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["share"])())
                        .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["catchError"])(function (err) { return Object(rxjs__WEBPACK_IMPORTED_MODULE_3__["of"])(err.error); }));
                };
                return HttpClientWrapperService;
            }());
            HttpClientWrapperService.ctorParameters = function () { return [
                { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"] }
            ]; };
            HttpClientWrapperService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
                    providedIn: 'root'
                })
            ], HttpClientWrapperService);
            /***/ 
        }),
        /***/ "./src/app/services/navigation-service/navigation.service.ts": 
        /*!*******************************************************************!*\
          !*** ./src/app/services/navigation-service/navigation.service.ts ***!
          \*******************************************************************/
        /*! exports provided: NavigationService */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "NavigationService", function () { return NavigationService; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var src_app_reusable_navigation__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/reusable/navigation */ "./src/app/reusable/navigation.ts");
            var NavigationService = /** @class */ (function () {
                function NavigationService() {
                    /* SETUP NAVIGATION HERE -------------------------------------------------------------------------------- */
                    this.APP_SECTION_MAIN = 0;
                    this.APP_SECTION_DASHBOARD = 1;
                    this.APP_SECTION_ACCOUNTS = 2;
                    this.APP_SECTION_DEPOSITS = 3;
                    this.APP_SECTION_EXCHANGE_RATES = 4;
                    this.navigation = new src_app_reusable_navigation__WEBPACK_IMPORTED_MODULE_2__["Navigation"]()
                        .withSectionNameAndIcon(this.APP_SECTION_MAIN, "Lists Editor", "app-section-main-icon")
                        .withSectionNameAndIcon(this.APP_SECTION_DASHBOARD, "Dashboard", "app-section-dashboard-icon")
                        .withSectionNameAndIcon(this.APP_SECTION_ACCOUNTS, "Accounts", "app-section-accounts-icon")
                        .withSectionNameAndIcon(this.APP_SECTION_DEPOSITS, "Deposits", "app-section-deposits-icon")
                        .withSectionNameAndIcon(this.APP_SECTION_EXCHANGE_RATES, "Exchange Rates", "app-section-exchange-rates-icon")
                        .startingAtSection(this.APP_SECTION_DASHBOARD)
                        .poppingStepsFromHistoryOnSectionGoBack(this.APP_SECTION_MAIN, 0);
                    /* ====================================================================================================== */
                    // Cache the current section so it doesn't have to be looked up into the navigation class all the time
                    this.currentSection = this.navigation.getCurrentSection();
                }
                NavigationService.prototype.getCurrentSection = function () {
                    return this.navigation.getCurrentSection();
                };
                NavigationService.prototype.getSections = function () {
                    return this.navigation.getSections();
                };
                NavigationService.prototype.navigateToSectionId = function (sectionId) {
                    this.navigation.navigateToSectionId(sectionId);
                    this.currentSection = this.navigation.getCurrentSection();
                };
                NavigationService.prototype.navigateBack = function () {
                    this.navigation.navigateBack();
                    this.currentSection = this.navigation.getCurrentSection();
                };
                return NavigationService;
            }());
            NavigationService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
                    providedIn: 'root'
                })
            ], NavigationService);
            /***/ 
        }),
        /***/ "./src/app/services/state-service/state.service.ts": 
        /*!*********************************************************!*\
          !*** ./src/app/services/state-service/state.service.ts ***!
          \*********************************************************/
        /*! exports provided: StateService */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "StateService", function () { return StateService; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var _data_service_data_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../data-service/data.service */ "./src/app/services/data-service/data.service.ts");
            /* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm2015/index.js");
            /* harmony import */ var src_app_model_account_record__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! src/app/model/account-record */ "./src/app/model/account-record.ts");
            /* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm2015/material.js");
            /* harmony import */ var src_app_components_dialog_error_dialog_error_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! src/app/components/dialog-error/dialog-error.component */ "./src/app/components/dialog-error/dialog-error.component.ts");
            /* harmony import */ var src_app_components_dialog_yes_no_dialog_yes_no_component__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! src/app/components/dialog-yes-no/dialog-yes-no.component */ "./src/app/components/dialog-yes-no/dialog-yes-no.component.ts");
            /* harmony import */ var _dialog_service_dialog_service__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../dialog-service/dialog.service */ "./src/app/services/dialog-service/dialog.service.ts");
            /* harmony import */ var src_app_model_nameable__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! src/app/model/nameable */ "./src/app/model/nameable.ts");
            /* harmony import */ var src_app_model_deposit__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! src/app/model/deposit */ "./src/app/model/deposit.ts");
            /* harmony import */ var src_app_model_monthly_balance_report_record__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! src/app/model/monthly-balance-report-record */ "./src/app/model/monthly-balance-report-record.ts");
            /* harmony import */ var src_app_reusable_params_builder__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! src/app/reusable/params.builder */ "./src/app/reusable/params.builder.ts");
            var StateService_1;
            var StateService = StateService_1 = /** @class */ (function () {
                function StateService(dataService, dialog, dialogService) {
                    this.dataService = dataService;
                    this.dialog = dialog;
                    this.dialogService = dialogService;
                    this.accounts = [];
                    this.accountSummariesLoaded = false;
                    // https://stackoverflow.com/questions/37587732/how-to-call-another-components-function-in-angular2
                    this.selectedRecordChangedMessageSource = new rxjs__WEBPACK_IMPORTED_MODULE_3__["BehaviorSubject"](null);
                    this.selectedRecordChangedObservable = this.selectedRecordChangedMessageSource.asObservable();
                    this.selectedDepositChangedMessageSource = new rxjs__WEBPACK_IMPORTED_MODULE_3__["BehaviorSubject"](null);
                    this.selectedDepositChangedObservable = this.selectedDepositChangedMessageSource.asObservable();
                    this.loadedDepositsMessageSource = new rxjs__WEBPACK_IMPORTED_MODULE_3__["BehaviorSubject"](null);
                    this.loadedDepositsMessageSourceObservable = this.loadedDepositsMessageSource.asObservable();
                    this.loadedMonthlyAccountBalanceReportSource = new rxjs__WEBPACK_IMPORTED_MODULE_3__["BehaviorSubject"](null);
                    this.loadedMonthlyAccountBalanceReportObservable = this.loadedMonthlyAccountBalanceReportSource.asObservable();
                    this.monthlyBalanceReport = [];
                    this.inlineHistoryReportMonths = 6;
                    this.monthlyDepositsBalanceReport = [];
                    this.exchageRatesHistory = new Map();
                    this.exchangeRatesHistoryLengthDays = 200;
                    this.allExchangeRatesHistoryLoaded = false;
                    this.firstDepositToReachMaturity = null;
                }
                StateService.prototype.loadMonthlyExpensesReports = function () {
                    var _this = this;
                    if (this.monthlyExpensesReports) {
                        return;
                    }
                    var params = new src_app_reusable_params_builder__WEBPACK_IMPORTED_MODULE_12__["ParamsBuilder"]().withNumberParam("startMonth", this.computeMonthNumberMinusXMonths(this.inlineHistoryReportMonths)).build();
                    this.dataService.get("reports/accounts/monthlyExpenses", params).subscribe(function (response) {
                        var errMsg = StateService_1.resultErrorMsg(response);
                        if (errMsg) {
                            _this.showErrDialog("ERROR: " + errMsg);
                        }
                        else {
                            _this.monthlyExpensesReports = response;
                        }
                    });
                };
                StateService.prototype.clearExchangeRatesHistory = function () {
                    this.exchageRatesHistory = new Map();
                    this.allExchangeRatesHistoryLoaded = false;
                };
                StateService.prototype.loadExchangeRatesHistory = function () {
                    var _this = this;
                    this.allExchangeRatesHistoryLoaded = false;
                    if (this.monitoredCurrencies) {
                        this.loadExchangeRatesHistoryForAllMonitoredCurrencies();
                    }
                    else {
                        this.loadMonitoredCurrency().subscribe(function (result) {
                            if (_this.monitoredCurrencies) {
                                _this.loadExchangeRatesHistoryForAllMonitoredCurrencies();
                            }
                        });
                    }
                };
                StateService.prototype.updateExchangeRatesHistoryRecordsState = function () {
                    var e_14, _a;
                    this.allExchangeRatesHistoryLoaded = false;
                    try {
                        for (var _b = __values(this.monitoredCurrencies), _c = _b.next(); !_c.done; _c = _b.next()) {
                            var c = _c.value;
                            if (!(this.exchageRatesHistory.get(c.currencyTypeName))) {
                                return;
                            }
                        }
                    }
                    catch (e_14_1) { e_14 = { error: e_14_1 }; }
                    finally {
                        try {
                            if (_c && !_c.done && (_a = _b.return)) _a.call(_b);
                        }
                        finally { if (e_14) throw e_14.error; }
                    }
                    this.allExchangeRatesHistoryLoaded = true;
                };
                StateService.prototype.loadExchangeRatesHistoryForAllMonitoredCurrencies = function () {
                    var e_15, _a;
                    try {
                        for (var _b = __values(this.monitoredCurrencies), _c = _b.next(); !_c.done; _c = _b.next()) {
                            var cr = _c.value;
                            this.loadExchangeRatesHistoryForCurrency(cr);
                        }
                    }
                    catch (e_15_1) { e_15 = { error: e_15_1 }; }
                    finally {
                        try {
                            if (_c && !_c.done && (_a = _b.return)) _a.call(_b);
                        }
                        finally { if (e_15) throw e_15.error; }
                    }
                };
                StateService.prototype.loadExchangeRatesHistoryForCurrency = function (currency) {
                    var _this = this;
                    if (this.exchageRatesHistory.get(currency.currencyTypeName)) {
                        this.updateExchangeRatesHistoryRecordsState();
                        return;
                    }
                    var params = new src_app_reusable_params_builder__WEBPACK_IMPORTED_MODULE_12__["ParamsBuilder"]()
                        .withNumberParam("currencyId", currency.id)
                        .withStringParam("sinceDate", this.computeDateMinusXDays(this.exchangeRatesHistoryLengthDays))
                        .build();
                    this.dataService.get("currency/listHistory", params).subscribe(function (result) {
                        var errMsg = StateService_1.resultErrorMsg(result);
                        if (errMsg) {
                            _this.showErrDialog("ERROR: " + errMsg);
                        }
                        else {
                            _this.exchageRatesHistory.set(currency.currencyTypeName, result);
                            _this.updateExchangeRatesHistoryRecordsState();
                        }
                    });
                };
                StateService.prototype.loadMonitoredCurrency = function () {
                    var _this = this;
                    var obs = this.dataService.get("currency/list");
                    obs.subscribe(function (monCr) {
                        _this.monitoredCurrencies = monCr;
                        _this.dataService.get("currency/listSupportedCurrencies").subscribe(function (supCr) {
                            var e_16, _a;
                            _this.supportedCurrencies = [];
                            try {
                                for (var supCr_1 = __values(supCr), supCr_1_1 = supCr_1.next(); !supCr_1_1.done; supCr_1_1 = supCr_1.next()) {
                                    var sc = supCr_1_1.value;
                                    if (!(_this.isCurrencyMonitored(sc))) {
                                        _this.supportedCurrencies.push(sc);
                                    }
                                }
                            }
                            catch (e_16_1) { e_16 = { error: e_16_1 }; }
                            finally {
                                try {
                                    if (supCr_1_1 && !supCr_1_1.done && (_a = supCr_1.return)) _a.call(supCr_1);
                                }
                                finally { if (e_16) throw e_16.error; }
                            }
                            _this.clearExchangeRatesHistory();
                        });
                    });
                    return obs;
                };
                StateService.prototype.monitorCurrency = function (currencyName) {
                    var _this = this;
                    var params = new src_app_reusable_params_builder__WEBPACK_IMPORTED_MODULE_12__["ParamsBuilder"]().withStringParam("currencyName", currencyName).build();
                    this.dataService.post("currency/monitorCurrency", params)
                        .subscribe(function (mCr) {
                        _this.monitoredCurrencies.push(mCr);
                        _this.popSupportedCurrency(currencyName);
                        _this.clearExchangeRatesHistory();
                    });
                };
                StateService.prototype.unMonitorCurrency = function (cr) {
                    var _this = this;
                    this.dataService.delete("currency", cr).subscribe(function (result) {
                        var errMsg = StateService_1.resultErrorMsg(result);
                        if (errMsg) {
                            _this.showErrDialog("ERROR: " + errMsg);
                        }
                        else {
                            _this.supportedCurrencies.push(cr.currencyTypeName);
                            _this.popMonitoredCurrency(cr);
                            _this.clearExchangeRatesHistory();
                        }
                    });
                };
                StateService.prototype.updateCurrenciesFromSource = function () {
                    var _this = this;
                    var obs = this.dataService.get("currency/updateCurrenciesFromSource");
                    obs.subscribe(function (result) {
                        var errMsg = StateService_1.resultErrorMsg(result);
                        if (errMsg) {
                            _this.showErrDialog("ERROR: " + errMsg);
                        }
                        else {
                            _this.loadMonitoredCurrency();
                        }
                    });
                    return obs;
                };
                StateService.prototype.popSupportedCurrency = function (spCrName) {
                    var e_17, _a;
                    var oldArr = this.supportedCurrencies;
                    this.supportedCurrencies = [];
                    try {
                        for (var oldArr_1 = __values(oldArr), oldArr_1_1 = oldArr_1.next(); !oldArr_1_1.done; oldArr_1_1 = oldArr_1.next()) {
                            var c = oldArr_1_1.value;
                            if (c != spCrName) {
                                this.supportedCurrencies.push(c);
                            }
                        }
                    }
                    catch (e_17_1) { e_17 = { error: e_17_1 }; }
                    finally {
                        try {
                            if (oldArr_1_1 && !oldArr_1_1.done && (_a = oldArr_1.return)) _a.call(oldArr_1);
                        }
                        finally { if (e_17) throw e_17.error; }
                    }
                };
                StateService.prototype.popMonitoredCurrency = function (mc) {
                    var e_18, _a;
                    var oldArr = this.monitoredCurrencies;
                    this.monitoredCurrencies = [];
                    try {
                        for (var oldArr_2 = __values(oldArr), oldArr_2_1 = oldArr_2.next(); !oldArr_2_1.done; oldArr_2_1 = oldArr_2.next()) {
                            var amc = oldArr_2_1.value;
                            if (amc.id != mc.id) {
                                this.monitoredCurrencies.push(amc);
                            }
                        }
                    }
                    catch (e_18_1) { e_18 = { error: e_18_1 }; }
                    finally {
                        try {
                            if (oldArr_2_1 && !oldArr_2_1.done && (_a = oldArr_2.return)) _a.call(oldArr_2);
                        }
                        finally { if (e_18) throw e_18.error; }
                    }
                };
                StateService.prototype.isCurrencyMonitored = function (crName) {
                    var e_19, _a;
                    try {
                        for (var _b = __values(this.monitoredCurrencies), _c = _b.next(); !_c.done; _c = _b.next()) {
                            var mCr = _c.value;
                            if (mCr.currencyTypeName == crName) {
                                return true;
                            }
                        }
                    }
                    catch (e_19_1) { e_19 = { error: e_19_1 }; }
                    finally {
                        try {
                            if (_c && !_c.done && (_a = _b.return)) _a.call(_b);
                        }
                        finally { if (e_19) throw e_19.error; }
                    }
                    return false;
                };
                StateService.prototype.loadMonthlyDepositsBalanceReport = function () {
                    var _this = this;
                    this.loadMonthlyBalanceReport(null, "deposits/monthlyBalance", function (ret) { _this.monthlyDepositsBalanceReport = ret; });
                };
                StateService.prototype.loadMonthlyBalanceReportForCurrentAccount = function () {
                    var _this = this;
                    if (!(this.selectedAccount)) {
                        return;
                    }
                    this.loadMonthlyBalanceReport(this.selectedAccount.id, "accounts/monthlyBalance", function (ret) { _this.monthlyBalanceReport = ret; });
                };
                StateService.prototype.loadMonthlyBalanceReport = function (accountId, reportPath, callback) {
                    // Build the params map for the request
                    var startMonth = this.computeMonthNumberMinusXMonths(this.inlineHistoryReportMonths);
                    var pBbuilder = new src_app_reusable_params_builder__WEBPACK_IMPORTED_MODULE_12__["ParamsBuilder"]().withNumberParam("startMonth", startMonth);
                    if (accountId) {
                        pBbuilder = pBbuilder.withNumberParam("accountId", accountId);
                    }
                    var params = pBbuilder.build();
                    // Send the requeest and process the result upon response arrival
                    this.dataService.get("reports/" + reportPath, params)
                        .subscribe(function (ret) {
                        var e_20, _a;
                        var targetArray = [];
                        try {
                            for (var ret_1 = __values(ret), ret_1_1 = ret_1.next(); !ret_1_1.done; ret_1_1 = ret_1.next()) {
                                var rec = ret_1_1.value;
                                targetArray.push(new src_app_model_monthly_balance_report_record__WEBPACK_IMPORTED_MODULE_11__["MonthlyBalanceReportRecord"](rec.recordYearMonth, rec.startingBalance, rec.incomingBalance, rec.outgoingBalance));
                            }
                        }
                        catch (e_20_1) { e_20 = { error: e_20_1 }; }
                        finally {
                            try {
                                if (ret_1_1 && !ret_1_1.done && (_a = ret_1.return)) _a.call(ret_1);
                            }
                            finally { if (e_20) throw e_20.error; }
                        }
                        callback(targetArray);
                    });
                };
                StateService.prototype.computeMonthNumberMinusXMonths = function (x) {
                    var today = new Date();
                    var xMonthsAgo = new Date();
                    xMonthsAgo.setTime(today.getTime() - (x * 20 * 24 * 60 * 60 * 1000));
                    var year = xMonthsAgo.getUTCFullYear();
                    var month = xMonthsAgo.getUTCMonth();
                    return (year * 100) + month;
                };
                StateService.prototype.computeDateMinusXDays = function (x) {
                    var d = new Date(new Date().getDate() - x);
                    return this.formatDate(d);
                };
                StateService.prototype.formatDate = function (d) {
                    return d.getUTCFullYear() + "-" + this.twoDigits(d.getMonth()) + "-" + this.twoDigits(d.getUTCDay());
                };
                StateService.prototype.twoDigits = function (n) {
                    if (n < 10) {
                        return "0" + n;
                    }
                    return "" + n;
                };
                StateService.prototype.loadDeposits = function () {
                    var _this = this;
                    if (this.deposits == null || this.deposits == undefined) {
                        this.dataService.list("deposits").subscribe(function (deposits) {
                            var errMsg = StateService_1.resultErrorMsg(deposits);
                            if (errMsg) {
                                _this.showErrDialog("ERROR : " + errMsg);
                                _this.selectDeposit(_this.selectedDeposit); // Select again to make sure the edit form is updated
                            }
                            else {
                                _this.deposits = deposits;
                                _this.selectedDeposit = null;
                                _this.loadedDepositsMessageSource.next(_this.deposits);
                                _this.loadMonthlyDepositsBalanceReport();
                                _this.cacheDepositsStatistics();
                            }
                        });
                    }
                };
                StateService.prototype.cacheDepositsStatistics = function () {
                    var e_21, _a;
                    this.totalDepositsValue = 0;
                    this.totalDepositsInterest = 0;
                    if (this.deposits && this.deposits.length > 0) {
                        this.firstDepositToReachMaturity = null;
                        try {
                            for (var _b = __values(this.deposits), _c = _b.next(); !_c.done; _c = _b.next()) {
                                var dep = _c.value;
                                // Cache the counters
                                this.totalDepositsValue += dep.value ? dep.value.valueOf() : 0;
                                this.totalDepositsInterest += dep.value && dep.interestPercent ? (dep.value.valueOf() * dep.interestPercent.valueOf() / 100) : 0;
                                // Find the first deposit to reach matureity
                                if (this.firstDepositToReachMaturity == null || this.firstDepositToReachMaturity.endDate > dep.endDate) {
                                    this.firstDepositToReachMaturity = dep;
                                }
                            }
                        }
                        catch (e_21_1) { e_21 = { error: e_21_1 }; }
                        finally {
                            try {
                                if (_c && !_c.done && (_a = _b.return)) _a.call(_b);
                            }
                            finally { if (e_21) throw e_21.error; }
                        }
                    }
                };
                StateService.prototype.newDeposit = function () {
                    if (this.accounts == null || this.accounts.length == 0) {
                        this.showErrDialog("At least one account must be registered before creating a deposit");
                        return;
                    }
                    if (this.banks == null || this.banks.length == 0) {
                        this.showErrDialog("At least one bank must be registered before creating a deposit");
                        return;
                    }
                    this.selectedDeposit = new src_app_model_deposit__WEBPACK_IMPORTED_MODULE_10__["Deposit"](0);
                    this.selectedDeposit.sourceAccountId = this.accounts[0].id;
                    this.selectedDeposit.bankId = this.banks[0].id;
                    this.selectedDeposit.accountNumber = "New deposit";
                    this.selectedDeposit.startDate = new Date();
                    this.selectedDeposit.endDate = this.addYears(this.selectedDeposit.startDate, 1);
                    this.deposits.push(this.selectedDeposit);
                    this.resetCatalogues();
                };
                StateService.prototype.resetCatalogues = function () {
                    this.incomeOrExpenseItemCategories = null;
                    this.incomeOrExpenseItems = null;
                };
                StateService.prototype.addYears = function (date, nYears) {
                    var year = date.getFullYear();
                    var month = date.getMonth();
                    var day = date.getDay();
                    return new Date(year + nYears, month, day);
                };
                StateService.prototype.selectDeposit = function (deposit) {
                    this.selectedDeposit = deposit;
                    this.selectedDepositChangedMessageSource.next(this.selectedDeposit); // propagate the message
                };
                StateService.prototype.saveSelectedDeposit = function (saveOnlyAccountNumber) {
                    var _this = this;
                    if (saveOnlyAccountNumber === void 0) { saveOnlyAccountNumber = false; }
                    this.dataService.save("deposits", this.selectedDeposit, (saveOnlyAccountNumber ? "saveAccountNumber" : null)).subscribe(function (deposit) {
                        var errMsg = StateService_1.resultErrorMsg(deposit);
                        if (errMsg) {
                            _this.showErrDialog("ERROR : " + errMsg);
                            _this.selectDeposit(_this.selectedDeposit); // Select again to make sure the edit form is updated
                        }
                        else {
                            _this.selectedDeposit.id = deposit.id;
                            _this.integrateIdentifiable(deposit, _this.deposits);
                            _this.selectedDeposit = null;
                        }
                    });
                };
                StateService.prototype.loadBanks = function () {
                    var _this = this;
                    if (this.banks == null || this.banks == undefined) {
                        this.dataService.list("banks").subscribe(function (banks) {
                            _this.banks = banks;
                        });
                    }
                };
                StateService.prototype.saveBank = function (bank) {
                    var _this = this;
                    this.dataService.save("banks", bank).subscribe(function (bnk) {
                        bank.id = bnk.id;
                        _this.integrateIdentifiable(bnk, _this.banks);
                    });
                };
                StateService.prototype.getBank = function (id) {
                    return this.getIdentifiable(this.banks, id);
                };
                StateService.prototype.getIdentifiable = function (itemsArray, id) {
                    var e_22, _a;
                    try {
                        for (var itemsArray_1 = __values(itemsArray), itemsArray_1_1 = itemsArray_1.next(); !itemsArray_1_1.done; itemsArray_1_1 = itemsArray_1.next()) {
                            var item = itemsArray_1_1.value;
                            if (item.id == id) {
                                return item;
                            }
                        }
                    }
                    catch (e_22_1) { e_22 = { error: e_22_1 }; }
                    finally {
                        try {
                            if (itemsArray_1_1 && !itemsArray_1_1.done && (_a = itemsArray_1.return)) _a.call(itemsArray_1);
                        }
                        finally { if (e_22) throw e_22.error; }
                    }
                    return null;
                };
                StateService.prototype.loadAccounts = function () {
                    var _this = this;
                    var ret = this.dataService.list("accounts");
                    ret.subscribe(function (result) {
                        _this.accounts = result;
                        _this.selectedAccount = (result[0] == null || result[0] == undefined) ? null : result[0];
                    });
                    return ret;
                };
                StateService.prototype.selectAccountId = function (accountId) {
                    var e_23, _a;
                    var _this = this;
                    try {
                        for (var _b = __values(this.accounts), _c = _b.next(); !_c.done; _c = _b.next()) {
                            var acc = _c.value;
                            if (acc.id == accountId) {
                                this.selectedAccount = acc;
                                this.clearSelectedAccountRecord();
                                break;
                            }
                        }
                    }
                    catch (e_23_1) { e_23 = { error: e_23_1 }; }
                    finally {
                        try {
                            if (_c && !_c.done && (_a = _b.return)) _a.call(_b);
                        }
                        finally { if (e_23) throw e_23.error; }
                    }
                    this.loadSelectedAccountSummary().subscribe(function (ret) {
                        _this.loadSelectedAccountRecords();
                    });
                    this.loadMonthlyBalanceReportForCurrentAccount();
                };
                StateService.prototype.saveAccount = function (account) {
                    var _this = this;
                    this.dataService.save("accounts", account).subscribe(function (acc) {
                        account.id = acc.id;
                        _this.integrateIdentifiable(account, _this.accounts);
                    });
                };
                StateService.prototype.getAccount = function (id) {
                    return this.getIdentifiable(this.accounts, id);
                };
                StateService.prototype.selectAccountRecord = function (rec) {
                    this.selectedAccountRecord = rec; // set the selected record
                    this.selectedRecordChangedMessageSource.next(this.selectedAccountRecord); // propagate the message
                };
                StateService.prototype.saveSelectedAccountRecord = function () {
                    var _this = this;
                    this.dataService.save("records", this.selectedAccountRecord)
                        .subscribe(function (rec) {
                        var errMsg = StateService_1.resultErrorMsg(rec);
                        if (errMsg) {
                            _this.showErrDialog("ERROR : " + errMsg);
                            _this.selectAccountRecord(_this.selectedAccountRecord); // Select again to make sure the edit form is updated
                        }
                        else {
                            _this.monthlyExpensesReports = null;
                            _this.selectedAccountRecord.id = rec.id;
                            // Reload the account summary since:
                            //    1) applying delta involves having a lot of code to maintain
                            //    2) the load on the server is not that high when fetching the summary
                            _this.loadSelectedAccountSummary();
                            // Clear the selected account record
                            _this.clearSelectedAccountRecord();
                        }
                    });
                };
                StateService.prototype.newAccountRecord = function () {
                    this.clearSelectedAccountRecord();
                    var rec = this.createNewAccountRecord();
                    this.selectedAccountRecords.push(rec);
                    this.selectAccountRecord(rec);
                };
                StateService.prototype.deleteAccountRecord = function (rec) {
                    var _this = this;
                    this.clearSelectedAccountRecord();
                    this.dialogService.dialogYesNoResponseObservable.subscribe(function (rsp) {
                        if (rsp) {
                            _this.dataService.delete("records", rec).subscribe(function (result) {
                                if (StateService_1.resultOk(result)) {
                                    _this.popAccountRecord(rec);
                                }
                                else {
                                    var errResult = result;
                                    _this.showErrDialog("ERROR: " + errResult.message);
                                }
                            });
                        }
                    });
                    this.showYesNoDialog("Really delete account record ?");
                };
                StateService.prototype.saveIncomeOrExpenseItem = function (item) {
                    var _this = this;
                    var obs = this.dataService.save("items", item);
                    obs.subscribe(function (ret) {
                        _this.incomeOrExpenseItems = null;
                        _this.loadIncomeOrExpenseItems();
                    });
                    return obs;
                };
                StateService.prototype.moneyTransfer = function (fromAccountId, toAccountId, amount) {
                    var _this = this;
                    var params = new src_app_reusable_params_builder__WEBPACK_IMPORTED_MODULE_12__["ParamsBuilder"]()
                        .withNumberParam("fromAccountId", fromAccountId)
                        .withNumberParam("toAccountId", toAccountId)
                        .withNumberParam("amount", amount)
                        .build();
                    this.dataService.get("accounts/transfer", params).subscribe(function (ret) {
                        var err = ret;
                        var errMsg = StateService_1.resultErrorMsg(err);
                        if (errMsg) {
                            _this.showErrDialog("ERROR: " + errMsg);
                        }
                        else {
                            _this.incomeOrExpenseItems = null;
                            _this.incomeOrExpenseItemCategories = null;
                            _this.loadIncomeOrExpenseItemCategories();
                            _this.loadIncomeOrExpenseItems();
                            if (_this.selectedAccount) {
                                if (_this.selectedAccount.id == fromAccountId || _this.selectedAccount.id == toAccountId) {
                                    _this.selectAccountId(_this.selectedAccount.id);
                                }
                            }
                        }
                    });
                };
                StateService.resultOk = function (result) {
                    return result && result.text == "OK";
                };
                StateService.resultErrorMsg = function (result) {
                    var err = result;
                    if (err && err.message) {
                        return err.message;
                    }
                    else {
                        return null;
                    }
                };
                StateService.prototype.showErrDialog = function (errStr) {
                    this.dialogService.dialogMessage = errStr;
                    this.dialogService.errDialogRef = this.dialog.open(src_app_components_dialog_error_dialog_error_component__WEBPACK_IMPORTED_MODULE_6__["DialogErrorComponent"], {
                        height: '150px',
                        width: '300px'
                    });
                };
                StateService.prototype.showYesNoDialog = function (msgStr) {
                    this.dialogService.dialogMessage = msgStr;
                    this.dialogService.yesNoDialogRef = this.dialog.open(src_app_components_dialog_yes_no_dialog_yes_no_component__WEBPACK_IMPORTED_MODULE_7__["DialogYesNoComponent"], {
                        height: '150px',
                        width: '300px'
                    });
                };
                StateService.prototype.popAccountRecord = function (rec) {
                    for (var r = 0; r < this.selectedAccountRecords.length; r++) {
                        if (this.selectedAccountRecords[r].id == rec.id) {
                            for (var rr = r; rr < this.selectedAccountRecords.length; rr++) {
                                this.selectedAccountRecords[rr] = this.selectedAccountRecords[rr + 1];
                            }
                            this.selectedAccountRecords.pop();
                            break;
                        }
                    }
                };
                StateService.prototype.createNewAccountRecord = function () {
                    var rec = new src_app_model_account_record__WEBPACK_IMPORTED_MODULE_4__["AccountRecord"](null);
                    rec.accountId = this.selectedAccount.id;
                    rec.date = new Date();
                    rec.incomeOrExpenseItemId = 0;
                    rec.value = 0;
                    return rec;
                };
                StateService.prototype.clearSelectedAccountRecord = function () {
                    this.selectedAccountRecord = null;
                    this.monthlyBalanceReport = [];
                };
                StateService.prototype.loadSummaryOfAllAccounts = function () {
                    var _this = this;
                    if (this.accounts.length > 0) {
                        this.loadSummaryOfAllAccountsPresumingAccountsHaveBeenLoaded();
                    }
                    else {
                        this.loadAccounts().subscribe(function (result) {
                            _this.loadSummaryOfAllAccountsPresumingAccountsHaveBeenLoaded();
                        });
                    }
                };
                StateService.prototype.loadSummaryOfAllAccountsPresumingAccountsHaveBeenLoaded = function () {
                    var e_24, _a;
                    var _this = this;
                    this.accountSummaries = [];
                    this.accountSummariesLoaded = false;
                    var _loop_2 = function (acc) {
                        this_1.loadAccountSummary(acc.id).subscribe(function (acSum) {
                            _this.accountSummaries[acc.id.valueOf()] = acSum;
                            _this.updateAccountSummariesStatus();
                        });
                    };
                    var this_1 = this;
                    try {
                        for (var _b = __values(this.accounts), _c = _b.next(); !_c.done; _c = _b.next()) {
                            var acc = _c.value;
                            _loop_2(acc);
                        }
                    }
                    catch (e_24_1) { e_24 = { error: e_24_1 }; }
                    finally {
                        try {
                            if (_c && !_c.done && (_a = _b.return)) _a.call(_b);
                        }
                        finally { if (e_24) throw e_24.error; }
                    }
                };
                StateService.prototype.updateAccountSummariesStatus = function () {
                    var e_25, _a;
                    this.accountSummariesLoaded = false;
                    try {
                        for (var _b = __values(this.accounts), _c = _b.next(); !_c.done; _c = _b.next()) {
                            var acc = _c.value;
                            if (!(this.accountSummaries[acc.id.valueOf()])) {
                                return;
                            }
                        }
                    }
                    catch (e_25_1) { e_25 = { error: e_25_1 }; }
                    finally {
                        try {
                            if (_c && !_c.done && (_a = _b.return)) _a.call(_b);
                        }
                        finally { if (e_25) throw e_25.error; }
                    }
                    this.accountSummariesLoaded = true;
                };
                StateService.prototype.loadSelectedAccountSummary = function () {
                    var _this = this;
                    var ret = this.loadAccountSummary(this.selectedAccount.id);
                    ret.subscribe(function (accountSummary) {
                        _this.selectedAccountSummary = accountSummary;
                    });
                    return ret;
                };
                StateService.prototype.loadAccountSummary = function (accountId) {
                    return this.dataService.get("accounts/summary", StateService_1.makeIdParam("accountId", accountId));
                };
                StateService.prototype.loadSelectedAccountRecords = function () {
                    var _this = this;
                    this.dataService.get("records/list", StateService_1.makeIdParam("accountId", this.selectedAccount.id))
                        .subscribe(function (ret) {
                        _this.selectedAccountRecords = ret;
                    });
                };
                StateService.prototype.loadIncomeOrExpenseItems = function () {
                    var _this = this;
                    if (this.incomeOrExpenseItems == null || this.incomeOrExpenseItems == undefined) {
                        this.loadList("items", (function (result) {
                            _this.incomeOrExpenseItems = _this.sortIncomeOrExpenseItems(result);
                        }));
                    }
                };
                StateService.prototype.getIncomeOrExpenseItem = function (itemId) {
                    var e_26, _a;
                    try {
                        for (var _b = __values(this.incomeOrExpenseItems), _c = _b.next(); !_c.done; _c = _b.next()) {
                            var item = _c.value;
                            if (item.id == itemId) {
                                return item;
                            }
                        }
                    }
                    catch (e_26_1) { e_26 = { error: e_26_1 }; }
                    finally {
                        try {
                            if (_c && !_c.done && (_a = _b.return)) _a.call(_b);
                        }
                        finally { if (e_26) throw e_26.error; }
                    }
                    return null;
                };
                StateService.prototype.sortIncomeOrExpenseItems = function (items) {
                    return items.sort(function (a, b) {
                        if (a.incomeOrExpenseItemCategoryId == b.incomeOrExpenseItemCategoryId) {
                            return (a.name < b.name ? -1 : 1);
                        }
                        else {
                            if (a.incomeOrExpenseItemCategoryId) {
                                return a.incomeOrExpenseItemCategoryId.valueOf() - b.incomeOrExpenseItemCategoryId.valueOf();
                            }
                            else {
                                return -1;
                            }
                        }
                    });
                };
                StateService.prototype.loadIncomeOrExpenseItemCategories = function () {
                    var _this = this;
                    if (this.incomeOrExpenseItemCategories == null || this.incomeOrExpenseItemCategories == undefined) {
                        this.loadList("categories", function (result) {
                            _this.incomeOrExpenseItemCategories = result;
                            _this.selectedIncomeOrExpenseItemCategory = _this.incomeOrExpenseItemCategories[0];
                        });
                    }
                };
                StateService.prototype.saveIncomeOrExpenseItemCategory = function (category) {
                    var _this = this;
                    var obs = this.dataService.save("categories", category);
                    obs.subscribe(function (cat) {
                        category.id = cat.id;
                        _this.integrateIdentifiable(cat, _this.incomeOrExpenseItemCategories);
                    });
                    return obs;
                };
                StateService.prototype.integrateIdentifiable = function (item, array) {
                    for (var c = 0; c < array.length; c++) {
                        if (array[c].id == item.id) {
                            array[c] = item;
                            return;
                        }
                    }
                    array.push(item);
                };
                StateService.prototype.loadList = function (wsPath, callback) {
                    this.dataService.list(wsPath).subscribe(function (items) {
                        var e_27, _a;
                        var newList = [];
                        try {
                            for (var items_1 = __values(items), items_1_1 = items_1.next(); !items_1_1.done; items_1_1 = items_1.next()) {
                                var item = items_1_1.value;
                                newList[item.id.valueOf()] = item;
                            }
                        }
                        catch (e_27_1) { e_27 = { error: e_27_1 }; }
                        finally {
                            try {
                                if (items_1_1 && !items_1_1.done && (_a = items_1.return)) _a.call(items_1);
                            }
                            finally { if (e_27) throw e_27.error; }
                        }
                        newList[0] = (new src_app_model_nameable__WEBPACK_IMPORTED_MODULE_9__["Nameable"](0, "Not selected"));
                        callback(newList);
                    });
                };
                StateService.makeIdParam = function (paramName, paramValue) {
                    return new Map([[paramName, paramValue.toFixed()]]);
                };
                return StateService;
            }());
            StateService.ctorParameters = function () { return [
                { type: _data_service_data_service__WEBPACK_IMPORTED_MODULE_2__["DataService"] },
                { type: _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatDialog"] },
                { type: _dialog_service_dialog_service__WEBPACK_IMPORTED_MODULE_8__["DialogService"] }
            ]; };
            StateService = StateService_1 = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
                    providedIn: 'root'
                })
            ], StateService);
            /***/ 
        }),
        /***/ "./src/assets/skins/dark-times/google-charts-style.ts": 
        /*!************************************************************!*\
          !*** ./src/assets/skins/dark-times/google-charts-style.ts ***!
          \************************************************************/
        /*! exports provided: GoogleChartsStyle */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "GoogleChartsStyle", function () { return GoogleChartsStyle; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            var GoogleChartsStyle = /** @class */ (function () {
                function GoogleChartsStyle() {
                }
                GoogleChartsStyle.createOptions = function () {
                    var ret = {
                        hAxis: {
                            textStyle: { color: '#999', fontSize: 9 },
                            slantedText: true,
                            slantedTextAngle: 90
                        },
                        vAxis: {
                            textStyle: { color: '#999', fontSize: 9 },
                            titleTextStyle: { color: "#999" },
                            viewWindowMode: 'explicit',
                            viewWindow: {
                                max: 3000,
                                min: 500
                            }
                        },
                        colors: ['#574', '#754', '#884'],
                        backgroundColor: "#282828",
                        legend: { position: 'top', textStyle: { color: '#AAA' } },
                        tooltip: { isHtml: true }
                    };
                    return ret;
                };
                return GoogleChartsStyle;
            }());
            /***/ 
        }),
        /***/ "./src/environments/environment.ts": 
        /*!*****************************************!*\
          !*** ./src/environments/environment.ts ***!
          \*****************************************/
        /*! exports provided: environment */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function () { return environment; });
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            // This file can be replaced during build by using the `fileReplacements` array.
            // `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
            // The list of file replacements can be found in `angular.json`.
            var environment = {
                production: false,
                skin: "dark-times" // Name of the skin
            };
            /*
             * For easier debugging in development mode, you can import the following file
             * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
             *
             * This import should be commented out in production mode because it will have a negative impact
             * on performance if an error is thrown.
             */
            // import 'zone.js/dist/zone-error';  // Included with Angular CLI.
            /***/ 
        }),
        /***/ "./src/main.ts": 
        /*!*********************!*\
          !*** ./src/main.ts ***!
          \*********************/
        /*! no exports provided */
        /***/ (function (module, __webpack_exports__, __webpack_require__) {
            "use strict";
            __webpack_require__.r(__webpack_exports__);
            /* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
            /* harmony import */ var hammerjs__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! hammerjs */ "./node_modules/hammerjs/hammer.js");
            /* harmony import */ var hammerjs__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/ __webpack_require__.n(hammerjs__WEBPACK_IMPORTED_MODULE_1__);
            /* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
            /* harmony import */ var _angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/platform-browser-dynamic */ "./node_modules/@angular/platform-browser-dynamic/fesm2015/platform-browser-dynamic.js");
            /* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./app/app.module */ "./src/app/app.module.ts");
            /* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");
            if (_environments_environment__WEBPACK_IMPORTED_MODULE_5__["environment"].production) {
                Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["enableProdMode"])();
            }
            Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_3__["platformBrowserDynamic"])().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_4__["AppModule"])
                .catch(function (err) { return console.error(err); });
            /***/ 
        }),
        /***/ 0: 
        /*!***************************!*\
          !*** multi ./src/main.ts ***!
          \***************************/
        /*! no static exports found */
        /***/ (function (module, exports, __webpack_require__) {
            module.exports = __webpack_require__(/*! /home/eu/FILES/Work/acct/workspace/acct-gui/src/main.ts */ "./src/main.ts");
            /***/ 
        })
    }, [[0, "runtime", "vendor"]]]);
//# sourceMappingURL=main-es2015.js.map
//# sourceMappingURL=main-es5.js.map
//# sourceMappingURL=main-es5.js.map