import instagramLogo from '../../assets/socialmedia/instagram-icon.png'
import twitterLogo from '../../assets/socialmedia/twitter-icon.png'
import linkedInLogo from '../../assets/socialmedia/linkedin-icon.png'

const SocialMediaButtons = () => {
    return (
        <section className="d-flex justify-content-end gap-2 social-media-tab">
            <a href='https://github.com/Valentino-La-Villa' target='_blank'>
                <img src={instagramLogo} alt="" />
            </a>
            <a href='https://github.com/Valentino-La-Villa' target='_blank'>
                <img src={twitterLogo} alt="" />
            </a>
            <a href='https://github.com/Valentino-La-Villa' target='_blank'>
                <img src={linkedInLogo} alt="" />
            </a>
        </section>
    )
}

export default SocialMediaButtons