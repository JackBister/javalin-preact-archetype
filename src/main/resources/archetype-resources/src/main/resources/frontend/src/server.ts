import { h } from "preact";
import { render as renderToString } from 'preact-render-to-string';
import { HomeComponent } from "./Home";

(global as any).renderToString = (component, props) => renderToString(h(component, props));

(global as any).HomeComponent = HomeComponent;