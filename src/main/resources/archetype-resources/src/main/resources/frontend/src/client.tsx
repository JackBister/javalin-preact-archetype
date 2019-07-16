import { h, render } from 'preact';
import { HomeComponent } from './Home';

function main() {
    const appRoot = document.getElementById('app');
    render(<HomeComponent name="Client" />, appRoot, appRoot.firstElementChild);
}

main();
