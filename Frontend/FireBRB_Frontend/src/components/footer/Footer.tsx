import SocialMediaButtons from "./SocialMediaButtons";
import Isologo from '../../assets/brand/Isologo.png';

export default function Footer() {
    return (
        <footer className="container-fluid bg-dark text-white d-flex justify-content-between align-items-center">
            <div className="d-flex align-items-center gap-2">
                <img src={Isologo} alt="" />
                <p>FireBRB 2024 all rights reserved™</p>
            </div>

            <SocialMediaButtons />
        </footer>
    )
}